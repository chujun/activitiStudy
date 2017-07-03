package com.jun.chu.demo.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Created by chujun on 2017/7/3.
 */
@Service
public class WorkFlowService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService    runtimeService;

    /**
     * 查询流程部署列表
     * 
     * @return
     */
    public List<Deployment> findDeployments() {
        return repositoryService.createDeploymentQuery().orderByDeploymenTime().asc().list();
    }

    /**
     * 查询流程定义列表
     * 
     * @return
     */
    public List<ProcessDefinition> findProcessDefinitions() {
        return repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
    }

    /**
     * 根据流程定义ID删除流程定义
     * 
     * @param processDefinitionId
     */
    public void deleteProcessDefinition(String processDefinitionId) {
        //根据流程定义ID查询流程定义信息
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        //根据部署ID删除部署信息
        if(null!= processDefinition){
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        }else{
            System.out.println("没有发现该流程定义:"+processDefinitionId);
        }
    }

    /**
     * 根据流程定义ID查询流程图
     * @param processDefinitionId
     * @return
     */
    public InputStream viewProcessDefinitionDiagram(String processDefinitionId){
        return repositoryService.getProcessDiagram(processDefinitionId);
    }
}
