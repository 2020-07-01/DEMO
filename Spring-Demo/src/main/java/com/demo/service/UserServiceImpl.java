package com.demo.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @ClassName : UserServiceImpl
 * @Author : yq
 * @Date: 2020-11-29
 * @Description :
 */
@Service
public class UserServiceImpl implements UserService, InitializingBean {

    private String desc;

    @Bean
    public void setDesc() {
        this.desc = "初始化desc......";
        System.out.println("初始化desc......");
    }

    @Override
    public String getUserServiceDesc() {
        return null;
    }


    @Override
    public void afterPropertiesSet() {

        System.out.println("initializingBean......");
    }
}
