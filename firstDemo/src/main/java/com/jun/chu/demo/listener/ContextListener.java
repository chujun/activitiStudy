package com.jun.chu.demo.listener;

import com.jun.chu.demo.util.ContextUtils;
import com.jun.chu.demo.util.JsonUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by chujun on 2017/7/3.
 */
@Component
public class ContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("监听到容器刷新成功事件:" + JsonUtils.toJson(event));
        //防止重复执行
        if(event.getApplicationContext().getParent() == null){
            ApplicationContext applicationContext = event.getApplicationContext();
            ContextUtils.setApplicationContext(applicationContext);
        }
    }
}
