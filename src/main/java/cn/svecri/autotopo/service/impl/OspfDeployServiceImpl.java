package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.util.TelnetClient;
import cn.svecri.autotopo.vo.Command;
import cn.svecri.autotopo.vo.CommandWithResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ospfDeployService")
public class OspfDeployServiceImpl implements TopoDeployService {

    @Autowired
    private TelnetClient routerA;
    @Autowired
    private TelnetClient routerB;
    @Autowired
    private TelnetClient routerC;

    @Override
    public List<Command> resolveConfiguration(String configuration) {
        return null;
    }

    @Override
    public List<CommandWithResult> exec(List<Command> commands) {
        return null;
    }

    @Override
    public List<CommandWithResult> exec(String configuration) {
        return exec(resolveConfiguration(configuration));
    }

    @Override
    public void clean() {

    }

    @Override
    public boolean running() {
        return false;
    }
}
