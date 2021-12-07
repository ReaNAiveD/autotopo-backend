package cn.svecri.autotopo.model;

import javax.persistence.*;

@Entity
@Table(indexes = { @Index(columnList = "topoId")})
public class TopoTestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int caseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topoId")
    private TopoInfo topoInfo;

    @Column(columnDefinition="VARCHAR(64)")
    private String cmd;

    @Column(columnDefinition="TEXT")
    private String expectedRe;

    @Column(columnDefinition="TEXT")
    private String targetTelnet;

    public TopoTestCase() {
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public TopoInfo getTopoInfo() {
        return topoInfo;
    }

    public void setTopoInfo(TopoInfo topoInfo) {
        this.topoInfo = topoInfo;
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
