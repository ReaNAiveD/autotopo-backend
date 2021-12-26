package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.model.TopoTestCase;
import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.vo.TestCaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Qualifier("ospfDeployService")
public class OspfDeployServiceImpl extends TopoDeployServiceBaseImpl{
    @Override
    public TestCaseResult runTestCase(List<TopoTestCase> testCaseList, boolean apply){return null;}
}
