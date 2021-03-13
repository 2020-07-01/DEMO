package com.rabbitmq.test.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : Controller
 * @Author : yq
 * @Date: 2020-12-06
 * @Description :
 */
@RestController
public class Controller {

    @Autowired
    PublisherMessage publisherMessage;


    @GetMapping(value = "/sendMessage")
    public String sendMessage() {

        publisherMessage.sendMessage();
        return "SUCCESS";
    }

}
