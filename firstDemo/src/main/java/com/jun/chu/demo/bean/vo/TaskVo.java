package com.jun.chu.demo.bean.vo;

import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;

import java.util.Date;
import java.util.Map;

/**
 * Created by chujun on 2017/7/3.
 */
public class TaskVo implements Task {
    private String id;

    private String name;

    private String description;

    private String owner;

    private String assignee;

    private String formKey;

    private String processDefinitionId;

    private String processInstanceId;

    private Date   createTime;

    private Date   claimTime;

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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setLocalizedName(String name) {

    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setLocalizedDescription(String description) {

    }

    @Override
    public void setPriority(int priority) {

    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    @Override
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public DelegationState getDelegationState() {
        return null;
    }

    @Override
    public void setDelegationState(DelegationState delegationState) {

    }

    @Override
    public void setDueDate(Date dueDate) {

    }

    @Override
    public void setCategory(String category) {

    }

    @Override
    public void setParentTaskId(String parentTaskId) {

    }

    @Override
    public void setTenantId(String tenantId) {

    }

    @Override
    public String getFormKey() {
        return formKey;
    }

    @Override
    public Map<String, Object> getTaskLocalVariables() {
        return null;
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return null;
    }

    @Override
    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    @Override
    public boolean isSuspended() {
        return false;
    }

    @Override
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    @Override
    public String getExecutionId() {
        return null;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String getTaskDefinitionKey() {
        return null;
    }

    @Override
    public Date getDueDate() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public String getParentTaskId() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }


}
