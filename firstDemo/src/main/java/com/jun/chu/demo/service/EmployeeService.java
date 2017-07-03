package com.jun.chu.demo.service;

import org.springframework.stereotype.Service;

/**
 * Created by chujun on 2017/7/3.
 */
@Service
public class EmployeeService {

    private static String departmentManager = "部门经理";

    private static String generalManager = "总经理";

    /**
     * 模拟用户和经理的关系,就不单独建表了
     * 
     * @param userName
     * @return
     */
    public String getManagerNameByUserName(String userName) {
        if (departmentManager.equals(userName)) {
            return generalManager;
        } else if (generalManager.equals(userName)) {
            return generalManager;
        } else {
            return departmentManager;
        }
    }
}
