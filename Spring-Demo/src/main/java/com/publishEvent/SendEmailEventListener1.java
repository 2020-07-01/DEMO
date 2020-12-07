package com.publishEvent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SendEmailEventListener1
 * @Author : yq
 * @Date: 2020-12-07
 * @Description :
 */
@Component
public class SendEmailEventListener1 implements ApplicationListener<SendEmailEvent> {

    @Override
    public void onApplicationEvent(SendEmailEvent event) {
        System.out.println(event.toString());
        System.out.println("监听器1");
    }
}
