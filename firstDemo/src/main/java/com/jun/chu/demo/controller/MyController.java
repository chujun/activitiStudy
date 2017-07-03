package com.jun.chu.demo.controller;

import com.jun.chu.demo.bean.vo.DeploymentVo;
import com.jun.chu.demo.bean.vo.ProcessDefinitionVo;
import com.jun.chu.demo.bean.WorkFlowBean;
import com.jun.chu.demo.bean.vo.TaskVo;
import com.jun.chu.demo.service.WorkFlowService;
import com.jun.chu.demo.util.JsonUtils;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public List<DeploymentVo> getTasks() {
        List<Deployment> deployments = workFlowService.findDeployments();
        return buildDeploymentBeanList(deployments);
    }

    @RequestMapping(value = "/processDefinitions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProcessDefinitionVo> getProcessDefinitions() {
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
                                             @PathVariable String processDefinitionId)
            throws IOException {
        System.out.println("workFlowBean:" + JsonUtils.toJson(workFlowBean));

        InputStream diagramInputStream = workFlowService.viewProcessDefinitionDiagram(processDefinitionId);
        outputDiagram(response, diagramInputStream);
    }

    @RequestMapping(value = "/processInstance/leaveBill/{leaveBillId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void startProcessInstance(@RequestBody WorkFlowBean workFlowBean, @PathVariable Long leaveBillId)
            throws IOException {
        System.out.println("workFlowBean:" + JsonUtils.toJson(workFlowBean));

        workFlowService.startProcessInstance(leaveBillId);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskVo> findTask(@RequestBody WorkFlowBean workFlowBean) throws IOException {
        System.out.println("workFlowBean:" + JsonUtils.toJson(workFlowBean));

        List<Task> tasks = workFlowService.findTaskByAssignee(workFlowBean.getUserName());
        return buildTaskVoList(tasks);
    }

    private List<TaskVo> buildTaskVoList(List<Task> tasks) {
        if (CollectionUtil.isEmpty(tasks)) {
            return Collections.emptyList();
        }
        List<TaskVo> result = new ArrayList<TaskVo>();
        for (Task item : tasks) {
            result.add(buildTaskVo(item));
        }
        return result;
    }

    private TaskVo buildTaskVo(Task item) {
        if (item == null) {
            return null;
        }
        TaskVo result = new TaskVo();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setCategory(item.getCategory());
        result.setAssignee(item.getAssignee());
        result.setOwner(item.getOwner());
        result.setCreateTime(item.getCreateTime());
        result.setClaimTime(item.getClaimTime());
        result.setFormKey(item.getFormKey());
        result.setPriority(item.getPriority());
        result.setProcessDefinitionId(item.getProcessDefinitionId());
        result.setProcessInstanceId(item.getProcessInstanceId());
        return result;
    }

    private List<ProcessDefinitionVo> buildProcessDefinitionBeanList(List<ProcessDefinition> processDefinitions) {
        if (CollectionUtil.isEmpty(processDefinitions)) {
            return Collections.emptyList();
        }
        List<ProcessDefinitionVo> result = new ArrayList<ProcessDefinitionVo>();
        for (ProcessDefinition item : processDefinitions) {
            result.add(buildProcessDefinitionBean(item));
        }
        return result;
    }

    private ProcessDefinitionVo buildProcessDefinitionBean(ProcessDefinition item) {
        if (item == null) {
            return null;
        }
        ProcessDefinitionVo result = new ProcessDefinitionVo();
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

    private List<DeploymentVo> buildDeploymentBeanList(List<Deployment> deployments) {
        if (CollectionUtil.isEmpty(deployments)) {
            return Collections.emptyList();
        }
        List<DeploymentVo> result = new ArrayList<DeploymentVo>();
        for (Deployment deployment : deployments) {
            result.add(buildDeploymentBean(deployment));
        }
        return result;
    }

    private DeploymentVo buildDeploymentBean(Deployment deployment) {
        if (deployment == null) {
            return null;
        }
        DeploymentVo result = new DeploymentVo();
        result.setId(deployment.getId());
        result.setName(deployment.getName());
        result.setCategory(deployment.getCategory());
        result.setDeploymentTime(deployment.getDeploymentTime());
        result.setKey(deployment.getKey());
        result.setTenantId(deployment.getTenantId());
        return result;
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
}
