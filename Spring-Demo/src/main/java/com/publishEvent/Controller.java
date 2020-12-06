package com.publishEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : Controller
 * @Author : yq
 * @Date: 2020-12-06
 * @Description :
 */
@RestController
@RequestMapping(value = "/event")
public class Controller {

    @Autowired
    SendEmailEventPublisher sendEmailEventPublisher;


    @GetMapping(value = "/register")
    public void registerUser(){
        System.out.println("用户注册成功......");
        SendEmailEvent sendEmailEvent = new SendEmailEvent(this);
        sendEmailEvent.setName("yq");
        sendEmailEvent.setEmail("123456789@qq.com");
        sendEmailEventPublisher.publisherSendEmailEvent(sendEmailEvent);
    }
}
