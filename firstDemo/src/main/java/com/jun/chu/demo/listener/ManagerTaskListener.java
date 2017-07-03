package com.jun.chu.demo.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * Created by chujun on 2017/7/3.
 */

public class ManagerTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("代理的任务信息:" + delegateTask);
    }
}
