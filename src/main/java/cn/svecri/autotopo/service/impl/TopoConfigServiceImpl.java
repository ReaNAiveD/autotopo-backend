package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.exception.NonDeletableException;
import cn.svecri.autotopo.exception.NotFoundException;
import cn.svecri.autotopo.model.TopoConf;
import cn.svecri.autotopo.model.TopoConfigSummary;
import cn.svecri.autotopo.model.TopoInfo;
import cn.svecri.autotopo.repository.TopoConfRepository;
import cn.svecri.autotopo.repository.TopoInfoRepository;
import cn.svecri.autotopo.repository.TopoTestCaseRepository;
import cn.svecri.autotopo.service.ControlPlaneService;
import cn.svecri.autotopo.service.TopoConfigService;
import cn.svecri.autotopo.service.TopoDeployService;
import cn.svecri.autotopo.vo.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TopoConfigServiceImpl implements TopoConfigService {

    private TopoConfRepository topoConfRepository;
    private TopoInfoRepository topoInfoRepository;
    private TopoTestCaseRepository topoTestCaseRepository;
    private ControlPlaneService controlPlaneService;

    @Autowired
    public void setTopoConfRepository(TopoConfRepository topoConfRepository) {
        this.topoConfRepository = topoConfRepository;
    }

    @Autowired
    public void setTopoInfoRepository(TopoInfoRepository topoInfoRepository) {
        this.topoInfoRepository = topoInfoRepository;
    }

    @Autowired
    public void setControlPlaneService(ControlPlaneService controlPlaneService) {
        this.controlPlaneService = controlPlaneService;
    }

    @Autowired
    public void setTopoTestCaseRepository(TopoTestCaseRepository topoTestCaseRepository) {
        this.topoTestCaseRepository = topoTestCaseRepository;
    }

    @Autowired
    @Qualifier("ospfDeployService")
    private TopoDeployService ospfDeployService;
    @Autowired
    @Qualifier("ripDeployService")
    private TopoDeployService ripDeployService;
    @Autowired
    @Qualifier("staticDeployService")
    private TopoDeployService staticDeployService;

    @Override
    public TopoConfigVo getDefaultTopoConfig(int topoId) {
        TopoConf topoConf = topoConfRepository.getFirstByTopoInfo_TopoIdAndIsDefaultTrueAndIsDeletedFalse(topoId);
        if (topoConf == null) {
            throw new NotFoundException("Default Config for Topo(id=" + topoId + ") Not Found");
        }
        return TopoConfigVo.fromTopoConf(topoConf);
    }

    @Override
    public TopoSchemaVo getTopoSchema(int topoId) {
        TopoInfo topoInfo = topoInfoRepository.getFirstByTopoId(topoId);
        if (topoInfo == null) {
            throw new NotFoundException("Topology(id=" + topoId + ") Not Found");
        }
        return TopoSchemaVo.fromTopoConf(topoInfo);
    }

    @Override
    public TopoConfigSaveResult saveTopoConfig(int topoId, String config) {
        TopoConf topoConf = new TopoConf(
                config,
                topoInfoRepository.getById(topoId)
        );
        topoConf =  topoConfRepository.save(topoConf);
        return new TopoConfigSaveResult(topoConf.getConfId());
    }

    @Override
    public TopoConfigVo getConfig(int configId) {
        TopoConf topoConf = topoConfRepository.getFirstByConfId(configId);
        if (topoConf == null) {
            throw new NotFoundException("Config(id=" + configId + ") Not Found");
        }
        return TopoConfigVo.fromTopoConf(topoConf);
    }

    @Override
    public TopoConfigListVo getAllTopoConfig() {
        List<TopoConfigSummary> topoConfigSummaryList = topoConfRepository.getAllByIsDeletedFalseOrderByLastUpdateDesc();
        return new TopoConfigListVo(topoConfigSummaryList.stream().map(TopoConfigListItemVo::fromTopoConfigView).collect(Collectors.toList()));
    }

    @Override
    public void deleteByConfigId(int configId) {
        TopoConf topoConf = topoConfRepository.getFirstByConfId(configId);
        if (topoConf == null) {
            throw new NotFoundException("Config(id=" + configId + ") Not Found");
        }
        if (topoConf.isDefault()) {
            throw new NonDeletableException("Cannot Delete Default Config");
        }
        topoConf.setDelete(true);
        topoConfRepository.save(topoConf);
    }

    @Override
    public TopoConfigApplyResult deployConfig(int configId) {
        TopoConf topoConf = topoConfRepository.getFirstByConfId(configId);
        return new TopoConfigApplyResult(controlPlaneService.deployTopo(topoConf.getTopoInfo().getTopoId(), topoConf.getContent()));
    }

    @Override
    public TestCaseResult testConfig(int topoId) {
        /*List<TestCaseResultItem> resultItems = new ArrayList<>();
        int success = 0;
        int total = 0;
        for (var testCase:
             topoTestCaseRepository.getAllByTopoInfo(topoInfoRepository.getById(topoId))) {
            var result = controlPlaneService.runTestCase(new Command(testCase.getTargetTelnet(), testCase.getCmd()));
            boolean pass = result.rsp.matches(testCase.getExpectedRe());
            resultItems.add(new TestCaseResultItem(result.device, result.cmd, result.rsp, testCase.getExpectedRe(), pass));
            if (pass) success++;
            total++;
        }
        return new TestCaseResult(((double) success) / total, resultItems);*/
        TestCaseResult result=null;
        switch (topoId){
            case 0:
                result=staticDeployService.runTestCase(topoTestCaseRepository.getAllByTopoInfo(topoInfoRepository.getById(topoId)));
                break;
            case 1:
                break;
            case 2:
                break;
            default:

        }
        return result;
    }

}
