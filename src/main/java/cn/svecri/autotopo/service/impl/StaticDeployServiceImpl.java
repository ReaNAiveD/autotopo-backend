package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.service.TopoDeployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Qualifier("staticDeployService")
public class StaticDeployServiceImpl extends TopoDeployServiceBaseImpl implements TopoDeployService {
    @Override
    public void clean() {
        log.info("in static clean");
    }
}
