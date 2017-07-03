package com.jun.chu.demo.controller;

import com.jun.chu.demo.bean.DeploymentBean;
import com.jun.chu.demo.bean.ProcessDefinitionBean;
import com.jun.chu.demo.service.WorkFlowService;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chujun on 2017/7/3.
 */
@RestController
public class MyController {
    @Autowired
    private WorkFlowService workFlowService;

    @RequestMapping(value = "/deployments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeploymentBean> getTasks() {
        List<Deployment> deployments = workFlowService.findDeployments();
        return buildDeploymentBeanList(deployments);
    }

    @RequestMapping(value = "/processDefinitions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProcessDefinitionBean> getProcessDefinitions() {
        List<ProcessDefinition> processDefinitions = workFlowService.findProcessDefinitions();
        return buildProcessDefinitionBeanList(processDefinitions);
    }

    private List<ProcessDefinitionBean> buildProcessDefinitionBeanList(List<ProcessDefinition> processDefinitions) {
        if(CollectionUtil.isEmpty(processDefinitions)){
            return Collections.emptyList();
        }
        List<ProcessDefinitionBean> result = new ArrayList<ProcessDefinitionBean>();
        for(ProcessDefinition item:processDefinitions){
            result.add(buildProcessDefinitionBean(item));
        }
        return result;
    }

    private ProcessDefinitionBean buildProcessDefinitionBean(ProcessDefinition item) {
        if (item == null) {
            return null;
        }
        ProcessDefinitionBean result = new ProcessDefinitionBean();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setKey(item.getKey());
        result.setDeploymentId(item.getDeploymentId());
        result.setDescription(item.getDescription());
        result.setResourceName(item.getResourceName());
        result.setDiagramResourceName(item.getDiagramResourceName());
        result.setVersion(item.getVersion());
        return result;
    }


    //////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


    private List<DeploymentBean> buildDeploymentBeanList(List<Deployment> deployments) {
        if(CollectionUtil.isEmpty(deployments)){
            return Collections.emptyList();
        }
        List<DeploymentBean> result = new ArrayList<DeploymentBean>();
        for(Deployment deployment:deployments){
            result.add(buildDeploymentBean(deployment));
        }
        return result;
    }

    private DeploymentBean buildDeploymentBean(Deployment deployment) {
        if (deployment == null) {
            return null;
        }
        DeploymentBean result = new DeploymentBean();
        result.setId(deployment.getId());
        result.setName(deployment.getName());
        result.setCategory(deployment.getCategory());
        result.setDeploymentTime(deployment.getDeploymentTime());
        result.setKey(deployment.getKey());
        result.setTenantId(deployment.getTenantId());
        return result;
    }
}
