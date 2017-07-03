package com.jun.chu.demo.listener;

import com.jun.chu.demo.service.EmployeeService;
import com.jun.chu.demo.util.ContextUtils;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.catalina.servlet4preview.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by chujun on 2017/7/3.
 */
@Component
public class ManagerTaskListener implements TaskListener {
    @Autowired
    EmployeeService employeeService;
    /**
     * 员工经理分配
     * 
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("代理的任务信息:" + delegateTask);
        //1.获取当前分配人
        String assignee = delegateTask.getAssignee();

        //2.获取员工服务
        //EmployeeService employeeService = (EmployeeService) ContextUtils.getBean("employeeService");

        //3.根据员工名称查询管理员名称
        String managerName = employeeService.getManagerNameByUserName(assignee);

        //4.设置任务的分配人
        delegateTask.setAssignee(managerName);
    }
}
