package com.jun.chu.demo.service;

import com.jun.chu.demo.bean.business.LeaveBill;
import com.jun.chu.demo.enm.LeaveBillStateEnum;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;

/**
 * Created by chujun on 2017/7/3.
 */
@Service
public class WorkFlowService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService    runtimeService;

    @Autowired
    private LeaveBillService  leaveBillService;

    @Autowired
    private TaskService       taskService;

    @Autowired
    private FormService       formService;

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
    @Transactional("business")
    public void deleteProcessDefinition(String processDefinitionId) {
        //根据流程定义ID查询流程定义信息
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        //根据部署ID删除部署信息
        if (null != processDefinition) {
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        } else {
            System.out.println("没有发现该流程定义:" + processDefinitionId);
        }
    }

    /**
     * 根据请假单ID启动流程实例
     */
    @Transactional("business")
    public void startProcessInstance(Long id) {
        LeaveBill leaveBill = leaveBillService.findLeaveBillById(id);
        //1.更新请假单的状态
        leaveBill.setState(LeaveBillStateEnum.AUDIT.getValue());
        leaveBillService.update(leaveBill);

        //2.获取当前操作人,存储到流程变量中去,后面流程给到分配人时需要用到
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("inputUser", leaveBill.getUserName());

        //3.生成业务key,格式:"LeaveBill.{id}"
        String businessKey = LeaveBill.class.getSimpleName();
        String businessKeyAndId = businessKey + "." + id;
        variables.put("objId", businessKeyAndId);

        //4.根据业务key和流程变量启动流程实例,同时指定businessKey
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(businessKey, businessKeyAndId,
                variables);
    }

    public List<Task> findTaskByAssignee(String userName) {
        return taskService.createTaskQuery().taskAssignee(userName).orderByTaskCreateTime().asc().list();
    }

    /**
     * 根据流程定义ID查询流程图
     * 
     * @param processDefinitionId
     * @return
     */
    public InputStream viewProcessDefinitionDiagram(String processDefinitionId) {
        return repositoryService.getProcessDiagram(processDefinitionId);
    }

    /**
     * 根据任务ID查询任务表单数据
     * 
     * @param taskId
     * @return
     */
    public TaskFormData getFormByTaskId(String taskId) {
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        return taskFormData;
    }

    /**
     * 根据任务ID查询业务key
     * 
     * @param taskId
     * @return
     */
    public String getBusinessKeyByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId()).singleResult();

        return processInstance.getBusinessKey();
    }

    /**
     * 根据任务ID查询流程定义中当前任务节点的所有输出流向的名称集合 TODO:cj to be studied:BpmnModel
     * 
     * @param taskId
     * @return
     */
    public List<String> getOutgoingFlowNames(String taskId) {
        //根据任务ID查询任务
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        //十分重要的流程定义对象
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());

        //选择当前活动节点
        UserTask userTask = selectUserTask(bpmnModel.getProcesses().get(0), task.getTaskDefinitionKey());

        //获取UserTask的所有输出流向的名称集合
        List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows();
        List<String> result = new ArrayList<String>();
        for (SequenceFlow sequenceFlow : outgoingFlows) {
            if(StringUtils.isNoneBlank(sequenceFlow.getName())){
                result.add(sequenceFlow.getName());
            }
        }
        return result;
    }

    /**
     * 根据节点名称选择流程定义中对应的用户任务节点
     * 
     * @param process
     * @param activityId
     * @return
     */
    private UserTask selectUserTask(Process process, String activityId) {
        System.out.println("selectUserTask:" + activityId);
        if (null == activityId) {
            return null;
        }
        Collection<FlowElement> flowElements = process.getFlowElements();
        for (FlowElement item : flowElements) {
            if (item instanceof UserTask) {
                UserTask userTask = (UserTask) item;
                if (userTask.getId().equals(activityId)) {
                    return userTask;
                }
            }
        }
        return null;
    }
}
