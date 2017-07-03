package com.jun.chu.demo.bean.vo;

import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;

import java.util.List;

/**
 * Created by chujun on 2017/7/3.
 */
public class TaskFormDataVo implements FormData {
    private String formKey;

    private String deploymentId;


    @Override
    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    @Override
    public String getDeploymentId() {
        return deploymentId;
    }

    @Override
    public List<FormProperty> getFormProperties() {
        return null;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
}
