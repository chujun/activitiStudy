package com.jun.chu.demo.bean.vo;

import org.activiti.engine.repository.ProcessDefinition;

/**
 * Created by chujun on 2017/7/3.
 */
public class ProcessDefinitionVo implements ProcessDefinition {
    private String id;

    private String name;

    private String key;

    private String category;

    private String description;

    private int version;

    private String resourceName;

    private String deploymentId;

    private String diagramResourceName;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    @Override
    public String getDiagramResourceName() {
        return diagramResourceName;
    }

    @Override
    public boolean hasStartFormKey() {
        return false;
    }

    @Override
    public boolean hasGraphicalNotation() {
        return false;
    }

    @Override
    public boolean isSuspended() {
        return false;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public String getEngineVersion() {
        return null;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
    }


}
