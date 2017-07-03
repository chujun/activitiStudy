package com.jun.chu.demo.service;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chujun on 2017/7/3.
 */
@Service
public class WorkFlowService {
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 查询流程部署列表
     * @return
     */
    public List<Deployment> findDeployments(){
        return repositoryService.createDeploymentQuery().orderByDeploymenTime().asc().list();
    }

    /**
     * 查询流程定义列表
     * @return
     */
    public List<ProcessDefinition> findProcessDefinitions(){
        return repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
    }
}
