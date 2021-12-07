package cn.svecri.autotopo.vo;

import cn.svecri.autotopo.model.TopoConf;

public class TopoConfigVo {

    public final int topo;
    public final String topoName;
    public final String config;

    public TopoConfigVo(int topo, String topoName, String config) {
        this.topo = topo;
        this.topoName = topoName;
        this.config = config;
    }

    public static TopoConfigVo fromTopoConf(TopoConf topoConf) {
        return new TopoConfigVo(topoConf.getTopoInfo().getTopoId(), topoConf.getTopoInfo().getName(), topoConf.getContent());
    }
}
