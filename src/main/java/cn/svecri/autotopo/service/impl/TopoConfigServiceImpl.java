package cn.svecri.autotopo.service.impl;

import cn.svecri.autotopo.exception.NonDeletableException;
import cn.svecri.autotopo.exception.NotFoundException;
import cn.svecri.autotopo.model.TopoConf;
import cn.svecri.autotopo.model.TopoConfigSummary;
import cn.svecri.autotopo.model.TopoInfo;
import cn.svecri.autotopo.repository.TopoConfRepository;
import cn.svecri.autotopo.repository.TopoInfoRepository;
import cn.svecri.autotopo.service.TopoConfigService;
import cn.svecri.autotopo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopoConfigServiceImpl implements TopoConfigService {

    private TopoConfRepository topoConfRepository;
    private TopoInfoRepository topoInfoRepository;

    @Autowired
    public void setTopoConfRepository(TopoConfRepository topoConfRepository) {
        this.topoConfRepository = topoConfRepository;
    }

    @Autowired
    public void setTopoInfoRepository(TopoInfoRepository topoInfoRepository) {
        this.topoInfoRepository = topoInfoRepository;
    }

    public TopoConfigVo getDefaultTopoConfig(int topoId) {
        TopoConf topoConf = topoConfRepository.getFirstByTopoInfo_TopoIdAndIsDefaultTrueAndIsDeletedFalse(topoId);
        if (topoConf == null) {
            throw new NotFoundException("Default Config for Topo(id=" + topoId + ") Not Found");
        }
        return TopoConfigVo.fromTopoConf(topoConf);
    }

    public TopoSchemaVo getTopoSchema(int topoId) {
        TopoInfo topoInfo = topoInfoRepository.getFirstByTopoId(topoId);
        if (topoInfo == null) {
            throw new NotFoundException("Topology(id=" + topoId + ") Not Found");
        }
        return TopoSchemaVo.fromTopoConf(topoInfo);
    }

    public TopoConfigSaveResult saveTopoConfig(int topoId, String config) {
        TopoConf topoConf = new TopoConf(
                config,
                topoInfoRepository.getById(topoId)
        );
        topoConf =  topoConfRepository.save(topoConf);
        return new TopoConfigSaveResult(topoConf.getConfId());
    }

    public TopoConfigVo getConfig(int configId) {
        TopoConf topoConf = topoConfRepository.getFirstByConfId(configId);
        if (topoConf == null) {
            throw new NotFoundException("Config(id=" + configId + ") Not Found");
        }
        return TopoConfigVo.fromTopoConf(topoConf);
    }

    public TopoConfigListVo getAllTopoConfig() {
        List<TopoConfigSummary> topoConfigSummaryList = topoConfRepository.getAllByIsDeletedFalseOrderByLastUpdateDesc();
        return new TopoConfigListVo(topoConfigSummaryList.stream().map(TopoConfigListItemVo::fromTopoConfigView).collect(Collectors.toList()));
    }

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

}
