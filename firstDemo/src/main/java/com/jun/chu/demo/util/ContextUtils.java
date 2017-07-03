package com.jun.chu.demo.util;

import org.springframework.context.ApplicationContext;

/**
 * Created by chujun on 2017/7/3.
 */
public class ContextUtils {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        if (null == applicationContext) {
            System.out.println("暂时还没有获取到spring applicationContext");
        }
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        applicationContext = applicationContext;
    }

    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);

    }
}
