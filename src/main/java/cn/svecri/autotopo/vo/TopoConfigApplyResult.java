package cn.svecri.autotopo.vo;

import java.util.List;

public class TopoConfigApplyResult {

    public final List<CommandWithResult> results;

    public TopoConfigApplyResult(List<CommandWithResult> results) {
        this.results = results;
    }
}
