package cn.svecri.autotopo.model;

import javax.persistence.Entity;

@Entity
public class TopoTestCase {
    
    private int caseId;

    private int topoId;

    private String cmd;

    private String expectedRe;

    private String targetTelnet;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getTopoId() {
        return topoId;
    }

    public void setTopoId(int topoId) {
        this.topoId = topoId;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getExpectedRe() {
        return expectedRe;
    }

    public void setExpectedRe(String expectedRe) {
        this.expectedRe = expectedRe;
    }

    public String getTargetTelnet() {
        return targetTelnet;
    }

    public void setTargetTelnet(String targetTelnet) {
        this.targetTelnet = targetTelnet;
    }

    

}
