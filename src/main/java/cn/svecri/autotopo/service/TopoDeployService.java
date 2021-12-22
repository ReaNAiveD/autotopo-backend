package cn.svecri.autotopo.service;

import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;
import cn.svecri.autotopo.vo.TelnetCommand;

import java.util.List;

public interface TopoDeployService {
    List<TelnetCommand> resolveConfiguration(String configuration);

    List<CommandWithResult> exec(List<Command> commands);

    List<CommandWithResult> exec(String configuration);

    void clean();

    boolean running();

}
