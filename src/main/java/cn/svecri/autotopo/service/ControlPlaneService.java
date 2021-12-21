package cn.svecri.autotopo.service;

import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;

import java.util.List;

public interface ControlPlaneService {

    List<CommandWithResult> deployTopo(int topoId, String conf);

    CommandWithResult runTestCase(Command testCases);

    TopoDeployService current();

}
