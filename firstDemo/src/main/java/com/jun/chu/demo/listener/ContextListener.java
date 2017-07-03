package com.jun.chu.demo.listener;

import com.jun.chu.demo.util.ContextUtils;
import com.jun.chu.demo.util.JsonUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by chujun on 2017/7/3.
 */
@Component
public class ContextListener implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("监听到容器启动成功事件:" + JsonUtils.toJson(event));

        ApplicationContext applicationContext = event.getApplicationContext();
        ContextUtils.setApplicationContext(applicationContext);
    }
}
