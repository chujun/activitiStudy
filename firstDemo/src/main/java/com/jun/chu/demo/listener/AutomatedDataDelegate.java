package com.jun.chu.demo.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Date;

/**
 * Created by chujun on 2017/6/26.
 */
public class AutomatedDataDelegate implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        Date now = new Date();
        execution.setVariable("autoWelcomeTime", now);
        System.out.println("Faux call to backend for ["
                + execution.getVariable("fullName") + "]");

    }
}
