package com.publishEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SendEmailEventPublisher
 * @Author : yq
 * @Date: 2020-12-06
 * @Description : 发送邮件事件发布
 */
@Component
public class SendEmailEventPublisher implements ApplicationEventPublisherAware {

    ApplicationEventPublisher eventPublisher;

    /**
     * 发布事件
     * @param sendEmailEvent
     */
    public void publisherSendEmailEvent(SendEmailEvent sendEmailEvent){
        eventPublisher.publishEvent(sendEmailEvent);
        System.out.println("发布事件成功.....");
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
