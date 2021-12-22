package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.exception.NoTopoDeployedException;
import cn.svecri.autotopo.exception.NotFoundException;
import cn.svecri.autotopo.exception.TopoRunningException;
import cn.svecri.autotopo.service.ControlPlaneService;
import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ControlPlaneServiceImpl implements ControlPlaneService {

    TopoDeployService current;

    @Autowired
    @Qualifier("ospfDeployService")
    private TopoDeployService ospfDeployService;
    @Autowired
    @Qualifier("ripDeployService")
    private TopoDeployService ripDeployService;
    @Autowired
    @Qualifier("staticDeployService")
    private TopoDeployService staticDeployService;

    private TopoDeployService getByTopoId(int topoId) {
        switch (topoId) {
            case 0:
                return staticDeployService;
            case 1:
                return ripDeployService;
            case 2:
                return ospfDeployService;
            default:
                throw new NotFoundException("Topo(topoId=" + topoId + ") Not Found");
        }
    }

    @Override
    public List<CommandWithResult> deployTopo(int topoId, String conf) {
        if (current != null) {
            if (current.running()) {
                throw new TopoRunningException("Topo is running. Cannot clean Now...");
            }
            current.clean();
        }
        current = getByTopoId(topoId);
        //return current.exec(conf);
        current.clean();
        return null;
    }

    @Override
    public CommandWithResult runTestCase(Command testCase) {
        if (current != null) {
            var testCases = new ArrayList<Command>();
            testCases.add(testCase);
            return current.exec(testCases).get(0);
        } else {
            throw new NoTopoDeployedException("No Topo Deployed");
        }
    }

    @Override
    public TopoDeployService current() {
        return current;
    }
}
