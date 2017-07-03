package com.jun.chu.demo.bean.business;

/**
 * Created by chujun on 2017/7/3.
 */
public class Employee {
    private String userName;

    private String managerName;

    private String department;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
