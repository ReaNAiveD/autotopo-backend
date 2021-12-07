package cn.svecri.autotopo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TopoConf {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int confId;

    private String content;

    private int topoId;

    private boolean isDefault;

    private boolean isdDelete;

    private LocalDateTime lastUpdate;

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

    public int getTopoId() {
        return topoId;
    }

    public void setTopoId(int topoId) {
        this.topoId = topoId;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean isIsdDelete() {
        return isdDelete;
    }

    public void setIsdDelete(boolean isdDelete) {
        this.isdDelete = isdDelete;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
