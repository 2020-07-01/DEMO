package com.publishEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SendEmailEventListener
 * @Author : yq
 * @Date: 2020-12-06
 * @Description : 监听者
 */
@Component
public class SendEmailEventListener implements ApplicationListener<SendEmailEvent> {

    /**
     * 处理监听到的事件
     * @param event
     */
    @Override
    public void onApplicationEvent(SendEmailEvent event) {
        System.out.println("监听到事件......");
        System.out.println(event.toString());
    }
}
