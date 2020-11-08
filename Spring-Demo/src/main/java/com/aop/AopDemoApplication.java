package com.aop;


import com.aop.service.ConsumerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName : AOPDemo
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */

public class AopDemoApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AopConfiguration.class);
        ConsumerService userDao = annotationConfigApplicationContext.getBean(ConsumerService.class);
        userDao.login("联想", "123");
    }
}
