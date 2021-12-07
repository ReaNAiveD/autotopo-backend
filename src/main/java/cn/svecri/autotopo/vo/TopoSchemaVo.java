package cn.svecri.autotopo.vo;

import cn.svecri.autotopo.model.TopoInfo;

public class TopoSchemaVo {

    public final int topo;
    public final String topoName;
    public final String schema;

    public TopoSchemaVo(int topo, String topoName, String schema) {
        this.topo = topo;
        this.topoName = topoName;
        this.schema = schema;
    }

    public static TopoSchemaVo fromTopoConf(TopoInfo topoInfo) {
        return new TopoSchemaVo(topoInfo.getTopoId(), topoInfo.getName(), topoInfo.getConfigSchema());
    }
}
