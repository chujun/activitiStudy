package com.jun.chu.demo.enm;

/**
 * Created by chujun on 2017/7/3.
 */
public enum LeaveBillStateEnum {
    INIT(0,"init"),
    AUDIT(1,"audit"),
    FINISH(2,"finishe"),
    ;
    private int value;

    private String desc;

    LeaveBillStateEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    LeaveBillStateEnum() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
