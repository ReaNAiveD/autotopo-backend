package cn.svecri.autotopo.service;

import cn.svecri.autotopo.model.TopoTestCase;
import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;
import cn.svecri.autotopo.vo.TestCaseResult;

import java.util.List;

public interface ControlPlaneService {

    List<CommandWithResult> deployTopo(int topoId, String conf, boolean apply);

    TestCaseResult runTestCase(List<TopoTestCase> list, boolean apply);

    TopoDeployService current();

    CommandWithResult executeSingle(List<Command> singletonList, boolean apply);
}
