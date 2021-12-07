package cn.svecri.autotopo.model;

import javax.persistence.*;

@Entity
public class TopoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topoId;

    @Column(columnDefinition="VARCHAR(64)")
    private String name;

    @Column(columnDefinition="TEXT")
    private String configSchema;

    public TopoInfo() {
    }

    public int getTopoId() {
        return topoId;
    }

    public void setTopoId(int topoId) {
        this.topoId = topoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfigSchema() {
        return configSchema;
    }

    public void setConfigSchema(String configSchema) {
        this.configSchema = configSchema;
    }

}