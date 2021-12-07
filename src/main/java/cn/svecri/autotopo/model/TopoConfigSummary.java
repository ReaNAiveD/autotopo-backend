package cn.svecri.autotopo.model;

import java.time.LocalDateTime;

public interface TopoConfigSummary {

    int getConfId();

    TopoInfoSummary getTopoInfo();

    boolean getIsDefault();

    LocalDateTime getLastUpdate();

    interface TopoInfoSummary {
        int getTopoId();

        String getName();
    }

}
