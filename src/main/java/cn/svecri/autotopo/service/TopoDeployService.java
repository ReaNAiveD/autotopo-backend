package cn.svecri.autotopo.service;

import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;

import java.util.List;

public interface TopoDeployService {

    List<Command> resolveConfiguration(String configuration);

    List<CommandWithResult> exec(List<Command> commands) throws Exception;

    List<CommandWithResult> exec(String configuration) throws Exception;

    void clean() throws Exception;

    boolean running();

}
