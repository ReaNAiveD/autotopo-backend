package cn.svecri.autotopo.service;

import cn.svecri.autotopo.vo.*;

import java.util.List;

public interface TopoConfigService {

    TopoConfigVo getDefaultTopoConfig(int topoId);

    TopoSchemaVo getTopoSchema(int topoId);

    TopoConfigSaveResult saveTopoConfig(int topoId, String config);

    TopoConfigVo getConfig(int configId);

    TopoConfigListVo getAllTopoConfig();

    void deleteByConfigId(int configId);

    TopoConfigApplyResult deployConfig(int configId);

    TestCaseResult testConfig(int topoId);

}
