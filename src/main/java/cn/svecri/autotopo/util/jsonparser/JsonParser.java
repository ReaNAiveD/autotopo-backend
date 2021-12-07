package cn.svecri.autotopo.util.jsonparser;

import cn.svecri.autotopo.util.TelnetClient;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConf;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConfItem;
import cn.svecri.autotopo.util.jsonparser.vo.PortDetail;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lmt
 * @date 2021/12/7 19:43
 */
public class JsonParser {
    Gson gson=new Gson();
    static String[] confFilePathList=new String[]{
            "D:\\UNIVERSE\\研一一\\高级计算机网络\\MINE\\大作业\\rip conf.json",
            "D:\\UNIVERSE\\研一一\\高级计算机网络\\MINE\\大作业\\static conf.json",
            "D:\\UNIVERSE\\研一一\\高级计算机网络\\MINE\\大作业\\ospf conf.json",
    };

    public DeviceConf parseJsonFromFile(String path) {
        String json="";
        String encoding = "UTF-8";
        File file=new File(path);
        long filelength = file.length();
        byte[] filecontent = new byte[(int)filelength];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            json=new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
        System.out.println(json);
        return gson.fromJson(json,DeviceConf.class);
    }


    public static void main(String[] args){
        JsonParser jsonParser=new JsonParser();
        ConfigurationApplyer configurationApplyer=new ConfigurationApplyer();
        DeviceConf deviceConf= jsonParser.parseJsonFromFile(confFilePathList[0]);

        DeviceConfItem[] confItems=new DeviceConfItem[3];
        confItems[0]=deviceConf.getRouterA();
        confItems[1]=deviceConf.getRouterB();
        confItems[2]=deviceConf.getRouterC();

        for(DeviceConfItem item:confItems) {
            TelnetClient client = configurationApplyer.connectTelnet(item);
            List<String> commandList = configurationApplyer.constructCommandList(item);
            String confReply = configurationApplyer.sendBatchCommand(client, commandList);
            System.out.println(confReply);
        }



    }

}
