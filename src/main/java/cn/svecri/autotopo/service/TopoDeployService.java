package cn.svecri.autotopo.service;

import cn.svecri.autotopo.model.TopoTestCase;
import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;
import cn.svecri.autotopo.vo.TelnetCommand;
import cn.svecri.autotopo.vo.TestCaseResult;

import java.util.List;

public interface TopoDeployService {
    List<TelnetCommand> resolveConfiguration(String configuration);

    List<CommandWithResult> exec(List<Command> commands, boolean apply);

    List<CommandWithResult> exec(String configuration, boolean apply);

    void clean(boolean apply);

    boolean running();

    TestCaseResult runTestCase(List<TopoTestCase> testCaseList, boolean apply);

}
