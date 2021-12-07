package cn.svecri.autotopo.vo;

public class Command {

    public final String device;
    public final String cmd;

    public Command(String device, String cmd) {
        this.device = device;
        this.cmd = cmd;
    }
}
