package cn.svecri.autotopo.model;

import javax.persistence.*;

@Entity
@Table
public class TopoCommand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cmdId;

    @Column(columnDefinition="VARCHAR(64)")
    private String cmd;

    @Column(columnDefinition="TEXT")
    private String targetTelnet;

    public TopoCommand() {
    }

    public TopoCommand(String cmd, String targetTelnet) {
        this.cmd = cmd;
        this.targetTelnet = targetTelnet;
    }

    public int getCmdId() {
        return cmdId;
    }

    public void setCmdId(int cmdId) {
        this.cmdId = cmdId;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getTargetTelnet() {
        return targetTelnet;
    }

    public void setTargetTelnet(String targetTelnet) {
        this.targetTelnet = targetTelnet;
    }

}
