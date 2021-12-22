package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.service.TopoDeployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Qualifier("ospfDeployService")
public class OspfDeployServiceImpl extends TopoDeployServiceBaseImpl implements TopoDeployService {
    @Override
    public void clean() {
        log.info("in ospf clean");
    }
}
