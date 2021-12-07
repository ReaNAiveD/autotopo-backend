package cn.svecri.autotopo.util;

import cn.svecri.autotopo.util.TelnetClient;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConfItem;
import cn.svecri.autotopo.util.jsonparser.vo.PortDetail;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lmt
 * @date 2021/12/7 20:56
 */
public class ConfigurationApplyer {
    public TelnetClient connectTelnet(DeviceConfItem deviceConfItem){
        TelnetClient client=new TelnetClient();
        PortDetail portDetail=null;
        for(PortDetail detail: deviceConfItem.getPort()){
            if("f0/0".equals(detail.getName())){
                portDetail=detail;
                break;
            }
        }
        try {
            if (client.login(portDetail.getIp(), 23, "CISCO")) {
                System.out.println("login");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
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
                    //TODO: convert mask; add ospf configuration
                    commandList.add("ip address "+portDetail.getIp()+" 255.255.255.0");
                    commandList.add("no shut");
                    commandList.add("exit");

                }
            }
            commandList.addAll(Arrays.asList(deviceConfItem.getCommand()));
        }
        return commandList;
    }

    public String sendBatchCommand(TelnetClient client,List<String> commandList){
        StringBuilder stringBuilder=new StringBuilder();
        for(String command:commandList) {
            String rs = client.sendCommand(command);
            try {
                //转编码
                rs = new String(rs.getBytes(StandardCharsets.ISO_8859_1), "GBK");
                stringBuilder.append(rs);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}
