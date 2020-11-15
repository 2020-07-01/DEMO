package com.demo.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @ClassName : AwareDemo
 * @Author : yq
 * @Date: 2020-11-15
 * @Description :
 */
@Service
public class ApplicationContextAwareDemo implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("执行ApplicationContentAware");
        this.applicationContext = applicationContext;
    }
}
