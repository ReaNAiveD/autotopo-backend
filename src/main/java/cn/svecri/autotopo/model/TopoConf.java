package cn.svecri.autotopo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class TopoConf {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int confId;

    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topoId")
    private TopoInfo topoInfo;

    private boolean isDefault;

    private boolean isDeleted;

    private LocalDateTime lastUpdate;

    public TopoConf(String content, TopoInfo topoInfo) {
        this.content = content;
        this.topoInfo = topoInfo;
        this.isDefault = false;
        this.isDeleted = false;
        this.lastUpdate = LocalDateTime.now();
    }

    public TopoConf() {}

    public int getConfId() {
        return confId;
    }

    public void setConfId(int confId) {
        this.confId = confId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TopoInfo getTopoInfo() {
        return topoInfo;
    }

    public void setTopoInfo(TopoInfo topoInfo) {
        this.topoInfo = topoInfo;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDelete(boolean delete) {
        isDeleted = delete;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
