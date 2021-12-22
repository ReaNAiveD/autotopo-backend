package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.util.ConfigurationApplyer;
import cn.svecri.autotopo.util.TelnetClient;
import cn.svecri.autotopo.util.jsonparser.JsonParser;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConf;
import cn.svecri.autotopo.util.jsonparser.vo.DeviceConfItem;
import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;
import cn.svecri.autotopo.vo.TelnetCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


@Slf4j
public class TopoDeployServiceBaseImpl implements TopoDeployService {
    ConfigurationApplyer configurationApplyer=new ConfigurationApplyer();
    private final List<TelnetClient> clientList=new ArrayList<>();
    private final AtomicBoolean isDeploying=new AtomicBoolean(false);

    @Override
    public List<TelnetCommand> resolveConfiguration(String configuration) {
        JsonParser jsonParser=new JsonParser();
        DeviceConf deviceConf= jsonParser.parseJsonFromString(configuration);

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
        List<CommandWithResult> res=null;
        isDeploying.set(true);
        for(TelnetCommand com:telnetCommandList) {
            res = configurationApplyer.sendBatchCommand(com.getClient(), com.getCmd());
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
}
