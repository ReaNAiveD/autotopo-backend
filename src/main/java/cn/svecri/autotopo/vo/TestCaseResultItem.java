package cn.svecri.autotopo.vo;

public class TestCaseResultItem {

    public final String device;
    public final String cmd;
    public final String actual;
    public final String expect;
    public final boolean pass;

    public TestCaseResultItem(String device, String cmd, String actual, String expect, boolean pass) {
        this.device = device;
        this.cmd = cmd;
        this.actual = actual;
        this.expect = expect;
        this.pass = pass;
    }
}
