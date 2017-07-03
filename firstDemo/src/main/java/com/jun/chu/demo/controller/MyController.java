package com.jun.chu.demo.controller;

import com.jun.chu.demo.bean.DeploymentBean;
import com.jun.chu.demo.bean.ProcessDefinitionBean;
import com.jun.chu.demo.bean.WorkFlowBean;
import com.jun.chu.demo.service.WorkFlowService;
import com.jun.chu.demo.util.JsonUtils;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    @RequestMapping(value = "/processDefinition/{processDefinitionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProcessDefinition(@RequestBody WorkFlowBean workFlowBean,
                                        @PathVariable String processDefinitionId) {
        System.out.println("workFlowBean:" + JsonUtils.toJson(workFlowBean));

        workFlowService.deleteProcessDefinition(processDefinitionId);
    }

    @RequestMapping(value = "/processDefinitionDiagram/{processDefinitionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void viewProcessDefinitionDiagram(HttpServletResponse response, @RequestBody WorkFlowBean workFlowBean,
                                             @PathVariable String processDefinitionId) throws IOException {
        System.out.println("workFlowBean:" + JsonUtils.toJson(workFlowBean));

        InputStream diagramInputStream = workFlowService.viewProcessDefinitionDiagram(processDefinitionId);
        outputDiagram(response, diagramInputStream);
    }

    private void outputDiagram(HttpServletResponse response, InputStream diagramInputStream) throws IOException {
        response.setContentType("image/png");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            byte[] data = new byte[1024];
            int length = -1;
            while ((length = diagramInputStream.read(data)) > -1) {
                outputStream.write(data);
            }
            diagramInputStream.close();
            outputStream.flush();
            outputStream.close();
        } finally {
            try {
                if (null != diagramInputStream) {
                    diagramInputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<ProcessDefinitionBean> buildProcessDefinitionBeanList(List<ProcessDefinition> processDefinitions) {
        if (CollectionUtil.isEmpty(processDefinitions)) {
            return Collections.emptyList();
        }
        List<ProcessDefinitionBean> result = new ArrayList<ProcessDefinitionBean>();
        for (ProcessDefinition item : processDefinitions) {
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
        if (CollectionUtil.isEmpty(deployments)) {
            return Collections.emptyList();
        }
        List<DeploymentBean> result = new ArrayList<DeploymentBean>();
        for (Deployment deployment : deployments) {
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
