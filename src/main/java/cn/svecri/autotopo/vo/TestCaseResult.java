package cn.svecri.autotopo.vo;

import java.util.List;

public class TestCaseResult {

    public final double rate;
    public final List<TestCaseResultItem> results;

    public TestCaseResult(double rate, List<TestCaseResultItem> results) {
        this.rate = rate;
        this.results = results;
    }
}
