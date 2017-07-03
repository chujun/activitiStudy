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

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static Object getBean(String beanId) {
        return getApplicationContext().getBean(beanId);

    }
}
