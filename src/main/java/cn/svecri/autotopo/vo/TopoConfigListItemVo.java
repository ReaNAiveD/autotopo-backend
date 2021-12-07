package cn.svecri.autotopo.vo;

import cn.svecri.autotopo.model.TopoConfigSummary;

import java.time.LocalDateTime;

public class TopoConfigListItemVo {

    public final int topo;
    public final String topoName;
    public final LocalDateTime lastUpdate;
    public final int configId;
    public final boolean isDefault;

    public TopoConfigListItemVo(int topo, String topoName, LocalDateTime lastUpdate, int configId, boolean isDefault) {
        this.topo = topo;
        this.topoName = topoName;
        this.lastUpdate = lastUpdate;
        this.configId = configId;
        this.isDefault = isDefault;
    }

    public static TopoConfigListItemVo fromTopoConfigView(TopoConfigSummary topoConfigSummary) {
        return new TopoConfigListItemVo(
                topoConfigSummary.getTopoInfo().getTopoId(),
                topoConfigSummary.getTopoInfo().getName(),
                topoConfigSummary.getLastUpdate(),
                topoConfigSummary.getConfId(),
                topoConfigSummary.getIsDefault()
        );
    }
}
