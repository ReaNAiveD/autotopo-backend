package cn.svecri.autotopo.vo;

public class CommandWithResult {

    public final String device;
    public final String cmd;
    public final String rsp;

    public CommandWithResult(String device, String cmd, String rsp) {
        this.device = device;
        this.cmd = cmd;
        this.rsp = rsp;
    }
}
