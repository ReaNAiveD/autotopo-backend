package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.model.TopoCommand;
import cn.svecri.autotopo.model.TopoTestCase;
import cn.svecri.autotopo.repository.TopoCommandRepository;
import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.util.ConfigurationApplyer;
import cn.svecri.autotopo.util.TelnetClient;
import cn.svecri.autotopo.util.jsonparser.JsonParser;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConf;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConfItem;
import cn.svecri.autotopo.util.jsonparser.vo.PortDetail;
import cn.svecri.autotopo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


@Slf4j
@Service
@Primary
public class TopoDeployServiceBaseImpl implements TopoDeployService {
    private TopoCommandRepository topoCommandRepository;

    @Autowired
    public void setTopoCommandRepository(TopoCommandRepository topoCommandRepository) {
        this.topoCommandRepository = topoCommandRepository;
    }

    ConfigurationApplyer configurationApplyer=new ConfigurationApplyer();

    @Autowired
    private TelnetClient routerA;
    @Autowired
    private TelnetClient routerB;
    @Autowired
    private TelnetClient routerC;

    //final List<TelnetClient> clientList=new ArrayList<>();
    final AtomicBoolean isDeploying=new AtomicBoolean(false);
    DeviceConf deviceConf;

    //分别是 A s0/0/0 B s0/0/0 s0/0/1 C s0/0/0 先写死了后面要改再说吧
    //key是 a0 b0 b1 c0
    Map<String,PortDetail> sPortList=new HashMap<>();

    private TelnetClient getClient(String deviceName){
        switch (deviceName){
            case "RouterA":
                return routerA;
            case "RouterB":
                return routerB;
            case "RouterC":
                return routerC;
            default:
                log.error("Invalid device name");
                return null;
        }
    }
    @Override
    public List<TelnetCommand> resolveConfiguration(String configuration) {
        JsonParser jsonParser=new JsonParser();
        deviceConf= jsonParser.parseJsonFromString(configuration);

        DeviceConfItem[] confItems= deviceConf.getRouter();
        List<TelnetCommand> telnetCommandList=new ArrayList<>();

        for(DeviceConfItem item:confItems) {
            List<String> commandList = configurationApplyer.constructCommandList(item);
            telnetCommandList.add(new TelnetCommand(getClient(item.getName()),commandList));
        }
        return telnetCommandList;
    }

    @Override
    public List<CommandWithResult> exec(List<Command> commands, boolean apply) {
        List<TelnetCommand> telnetCommandList=new ArrayList<>();
        for(Command com:commands) {
            log.info(com.device+" "+com.cmd);
            TelnetCommand telnetCommand=new TelnetCommand();
            telnetCommand.setClient(getClient(com.device));
            telnetCommand.setCmd(Collections.singletonList(com.cmd));
            telnetCommandList.add(telnetCommand);
        }
        return execute(telnetCommandList, apply);
    }


    private List<CommandWithResult> execute(List<TelnetCommand> telnetCommandList, boolean apply) {
        List<CommandWithResult> res=new ArrayList<>();
        isDeploying.set(true);
        for(TelnetCommand com:telnetCommandList) {
            res.addAll(configurationApplyer.sendBatchCommand(com.getClient(), com.getCmd(), topoCommandRepository, apply));
        }
        isDeploying.set(false);
        return res;
    }

    @Override
    public List<CommandWithResult> exec(String configuration, boolean apply) {
        return execute(resolveConfiguration(configuration), apply);
    }

    @Override
    public void clean(boolean apply) {
        log.info("in base clean");
        isDeploying.set(true);
        //添加client
        List<TelnetCommand> telnetCommandList = new ArrayList<>();
        for (int i=0;i<3;i++) {
            TelnetClient client;
            switch (i){
                case 0:
                    client=routerA;break;
                case 1:
                    client=routerB;break;
                case 2:
                    client=routerC;break;
                default:
                    client=null;
            }
            //数据库中获取的
            List<TopoCommand> topoCmdList = topoCommandRepository.getAllByTargetTelnet(client.getDeviceName());
            List<String> commandList = new ArrayList<>();
            for(TopoCommand item : topoCmdList) {
                String initCmd = item.getCmd();
                String targetCmd = "";
                if(initCmd.contains("no") && initCmd.contains("shut")){
                    targetCmd = "shutdown";
                }
                else{
                    if(initCmd.contains("ip") || initCmd.contains("network") || initCmd.contains("area")){
                        targetCmd = "no " + initCmd;
                    }
                    else if(!initCmd.contains("redistribute")){
                        targetCmd = initCmd;
                    }
                }
                commandList.add(targetCmd);
            }
            telnetCommandList.add(new TelnetCommand(client,commandList));
        }
        execute(telnetCommandList, false);
        topoCommandRepository.deleteAllInBatch();
        isDeploying.set(false);
    }

    @Override
    public boolean running() {
        return isDeploying.get();
    }

    /**
     * 静态路由和rip这一段可以复用，如果ospf不行就直接override吧
     */
    @Override
    public TestCaseResult runTestCase(List<TopoTestCase> testCaseList, boolean apply){
        getPortDetail();

        List<TestCaseResultItem> resultItems = new ArrayList<>();
        int success = 0;
        int total = 0;
        boolean pass;
        List<CommandWithResult> result;
        String cmdStr="";
        String rexpStr="";

        for(TopoTestCase testCase:testCaseList) {
            //打开的s口应该可以互相ping通 下面添加的是ping命令
            if(testCase.getCmd().startsWith("ping ")) {
                for(String ip:sPortList.values().stream().map(PortDetail::getIp).collect(Collectors.toList())) {
                    cmdStr=testCase.getCmd().replace("#1",ip);
                    rexpStr=testCase.getExpectedRe();
                    result = exec(Collections.singletonList(
                            new Command(testCase.getTargetTelnet(), cmdStr)), apply);
                    pass = result.get(0).rsp.matches(rexpStr);
                    resultItems.add(new TestCaseResultItem(
                            result.get(0).device, cmdStr, result.get(0).rsp, rexpStr, pass));
                    if (pass) {success++;}
                    total++;
                }
            }else if(testCase.getCmd().equals("show ip route")){
                //show ip route 命令
                cmdStr=testCase.getCmd();
                result=exec(Collections.singletonList(new Command(testCase.getTargetTelnet(), cmdStr)), apply);
                rexpStr=concatCommand(testCase.getTargetTelnet(), testCase.getExpectedRe());
                pass = result.get(0).rsp.matches(rexpStr);
                resultItems.add(new TestCaseResultItem(result.get(0).device, cmdStr, result.get(0).rsp, rexpStr, pass));
                if (pass) {success++;}
                total++;
            }else if(testCase.getCmd().equals("show ip ospf database")){
                //show ip ospf database命令
                cmdStr=testCase.getCmd();
                result = exec(Collections.singletonList(new Command(testCase.getTargetTelnet(), cmdStr)), apply);
                if(testCase.getTargetTelnet().equals("RouterA")){
                    pass = result.get(0).rsp.contains("Router Link States")
                            && result.get(0).rsp.contains("Net Link States")
                            && result.get(0).rsp.contains("Summary Net Link States")
                            && result.get(0).rsp.contains("Summary ASB Link States")
                            && result.get(0).rsp.contains("Type-5 AS External Link States");
                }else if(testCase.getTargetTelnet().equals("RouterB")){
                    pass = result.get(0).rsp.contains("Router Link States")
                            && result.get(0).rsp.contains("Net Link States")
                            && result.get(0).rsp.contains("Summary Net Link States")
                            && result.get(0).rsp.contains("Summary ASB Link States")
                            && result.get(0).rsp.contains("Type-7 AS External Link States")
                            && result.get(0).rsp.contains("Type-5 AS External Link States");
                }else{
                    //RouterC
                    pass = result.get(0).rsp.contains("Router Link States")
                            && result.get(0).rsp.contains("Summary Net Link States")
                            && result.get(0).rsp.contains("Type-7 AS External Link States");
                }
                resultItems.add(new TestCaseResultItem(result.get(0).device, cmdStr, result.get(0).rsp, rexpStr, pass));
                if (pass) {success++;}
                total++;
            }
        }
        return new TestCaseResult(((double) success) / total, resultItems);
    }

    private void getPortDetail() {
        int index=0;
        while(index<deviceConf.getRouter().length) {
            log.info(deviceConf.getRouter().length+"");
            for (PortDetail portDetail : deviceConf.getRouter()[index].getPort()) {
                log.info(portDetail.getName()+" "+portDetail.getIp());
                //打开的s口
                if (portDetail.isUp() && portDetail.getName().startsWith("s")) {
                    String deviceName=deviceConf.getRouter()[index].getName();
                    String portName=portDetail.getName();
                    sPortList.put(
                            deviceName.substring(deviceName.length()-1).toLowerCase()+portName.substring(portName.length()-1),
                            portDetail
                    );
                }
            }
            index++;
        }
        if(sPortList.size()!=4){
            log.error("not enough open port!");
        }
    }

    public String concatCommand(String deviceName,String originRe){return "";}
}
