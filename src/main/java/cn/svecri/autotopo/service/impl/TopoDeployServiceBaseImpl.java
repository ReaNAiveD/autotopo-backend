package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.model.TopoTestCase;
import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.util.ConfigurationApplyer;
import cn.svecri.autotopo.util.TelnetClient;
import cn.svecri.autotopo.util.jsonparser.JsonParser;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConf;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConfItem;
import cn.svecri.autotopo.util.jsonparser.vo.PortDetail;
import cn.svecri.autotopo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


@Slf4j
@Service
@Primary
public class TopoDeployServiceBaseImpl implements TopoDeployService {
    ConfigurationApplyer configurationApplyer=new ConfigurationApplyer();
    final List<TelnetClient> clientList=new ArrayList<>();
    final AtomicBoolean isDeploying=new AtomicBoolean(false);
    DeviceConf deviceConf;

    //分别是 A s0/0/0 B s0/0/0 s0/0/1 C s0/0/0 先写死了后面要改再说吧
    //key是 a0 b0 b1 c0
    Map<String,PortDetail> sPortList=new HashMap<>();

    @Override
    public List<TelnetCommand> resolveConfiguration(String configuration) {
        JsonParser jsonParser=new JsonParser();
        deviceConf= jsonParser.parseJsonFromString(configuration);

        DeviceConfItem[] confItems= deviceConf.getRouter();
        List<TelnetCommand> telnetCommandList=new ArrayList<>();

        for(DeviceConfItem item:confItems) {
            TelnetClient client = configurationApplyer.connectTelnet(item);
            clientList.add(client);
            List<String> commandList = configurationApplyer.constructCommandList(item);
            telnetCommandList.add(new TelnetCommand(client,commandList));
        }
        return telnetCommandList;
    }

    @Override
    public List<CommandWithResult> exec(List<Command> commands) {
        List<TelnetCommand> telnetCommandList=new ArrayList<>();
        for(Command com:commands) {
            for (TelnetClient client : clientList) {
                if (client.getDeviceName().equals(com.device)){
                    TelnetCommand telnetCommand=new TelnetCommand();
                    telnetCommand.setClient(client);
                    telnetCommand.setCmd(Collections.singletonList(com.cmd));
                    telnetCommandList.add(telnetCommand);
                }
            }
        }
        return execute(telnetCommandList);
    }


    private List<CommandWithResult> execute(List<TelnetCommand> telnetCommandList) {
        List<CommandWithResult> res=new ArrayList<>();
        isDeploying.set(true);
        for(TelnetCommand com:telnetCommandList) {
            res.addAll(configurationApplyer.sendBatchCommand(com.getClient(), com.getCmd()));
        }
        isDeploying.set(false);
        return res;
    }

    @Override
    public List<CommandWithResult> exec(String configuration) {
        return execute(resolveConfiguration(configuration));
    }

    @Override
    public void clean() {log.info("in base clean");}

    @Override
    public boolean running() {
        return isDeploying.get();
    }

    /**
     * 静态路由和rip这一段可以复用，如果ospf不行就直接override吧
     */
    @Override
    public TestCaseResult runTestCase(List<TopoTestCase> testCaseList){
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
                            new Command(testCase.getTargetTelnet(), cmdStr)));
                    pass = result.get(0).rsp.matches(rexpStr);
                    resultItems.add(new TestCaseResultItem(
                            result.get(0).device, cmdStr, result.get(0).rsp, rexpStr, pass));
                    if (pass) {success++;}
                    total++;
                }
            }else{
                //show ip route 命令
                cmdStr=testCase.getCmd();
                result=exec(Collections.singletonList(new Command(testCase.getTargetTelnet(), cmdStr)));
                rexpStr=concatCommand(testCase.getTargetTelnet(), testCase.getExpectedRe());
                pass = result.get(0).rsp.matches(rexpStr);
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
            for (PortDetail portDetail : deviceConf.getRouter()[index].getPort()) {
                //打开的s口
                if (portDetail.isUp() && portDetail.getName().startsWith("s")) {
                    String deviceName=deviceConf.getRouter()[index].getName();
                    String portName=portDetail.getName();
                    sPortList.put(
                            deviceName.substring(deviceName.length()-1)+portName.substring(portName.length()-1),
                            portDetail
                    );
                }
                index++;
            }
        }
        if(sPortList.size()!=4){
            log.error("not enough open port!");
        }
    }

    public String concatCommand(String deviceName,String originRe){return "";}
}
