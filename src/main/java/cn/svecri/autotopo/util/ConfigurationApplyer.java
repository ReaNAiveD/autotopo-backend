package cn.svecri.autotopo.util;

import cn.svecri.autotopo.model.TopoCommand;
import cn.svecri.autotopo.repository.TopoCommandRepository;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConfItem;
import cn.svecri.autotopo.util.jsonparser.vo.PortDetail;
import cn.svecri.autotopo.vo.CommandWithResult;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lmt
 * @date 2021/12/7 20:56
 */
@Slf4j
public class ConfigurationApplyer {
    public TelnetClient connectTelnet(DeviceConfItem deviceConfItem){
        TelnetClient client=new TelnetClient("VT220","#",deviceConfItem.getName());
//        TelnetClient client=new TelnetClient("VT220","#");
        PortDetail portDetail=null;
        for(PortDetail detail: deviceConfItem.getPort()){
            if("f0/0".equals(detail.getName())){
                portDetail=detail;
                break;
            }
        }
        if(portDetail!=null) {
            if (client.login(portDetail.getIp(), 23, "CISCO")) {
                System.out.println("login");
            }
        }else{
            log.error("f0/0 port is null");
        }
        return client;
    }

    public List<String> constructCommandList(DeviceConfItem deviceConfItem){
        ArrayList<String> commandList=new ArrayList<>();
        if(deviceConfItem!=null){
            commandList.add("conf t");
            for(PortDetail portDetail: deviceConfItem.getPort()){
                if(!"f0/0".equals(portDetail.getName())) {
                    commandList.add("int "+portDetail.getName());
                    if(portDetail.isUp()) {
                        commandList.add("ip address " + portDetail.getIp() + " "
                                +NetmaskConverter.masknum2str(portDetail.getMask()));
                        commandList.add("no shut");
                    }else{
                        commandList.add("shutdown");
                    }
                    commandList.add("exit");

                }
            }
            commandList.addAll(Arrays.asList(deviceConfItem.getCommand()));
            commandList.add("end");
            //??????conf ??????????????????
        }
        return commandList;
    }

    public List<CommandWithResult> sendBatchCommand(TelnetClient client, List<String> commandList, TopoCommandRepository topoCommandRepository, boolean apply){
        List<CommandWithResult> res=new ArrayList<>();
        for(String command:commandList) {
            log.info(command);
            String rs = client.sendCommand(command);
            if(apply){
                if(command.contains("no")){
                    if(command.contains("shut")){
                        TopoCommand topoCommand = new TopoCommand(
                                command,
                                client.getDeviceName()
                        );
                        topoCommandRepository.save(topoCommand);
                    }
                }
                else{
                    if(!command.contains("show") && !command.contains("ping") && !command.contains("term len")){
                        TopoCommand topoCommand = new TopoCommand(
                                command,
                                client.getDeviceName()
                        );
                        topoCommandRepository.save(topoCommand);
                    }
                }
            }
            log.info(rs);
            try {
                //?????????
                rs = new String(rs.getBytes(StandardCharsets.ISO_8859_1), "GBK");
                res.add(new CommandWithResult(client.getDeviceName(),command,rs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
