package cn.svecri.autotopo.service;

import cn.svecri.autotopo.vo.TopoConfigListVo;
import cn.svecri.autotopo.vo.TopoConfigSaveResult;
import cn.svecri.autotopo.vo.TopoConfigVo;
import cn.svecri.autotopo.vo.TopoSchemaVo;

public interface TopoConfigService {

    TopoConfigVo getDefaultTopoConfig(int topoId);

    TopoSchemaVo getTopoSchema(int topoId);

    TopoConfigSaveResult saveTopoConfig(int topoId, String config);

    TopoConfigVo getConfig(int configId);

    TopoConfigListVo getAllTopoConfig();

    void deleteByConfigId(int configId);

}
