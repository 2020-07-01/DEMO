package com.aop.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName : ConsumerServiceImpl
 * @Author : yq
 * @Date: 2020-11-08
 * @Description :
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    /**
     * 用户登录
     *
     * @param name
     * @param password
     */
    @Override
    public void login(String name, String password) {

        System.out.println("用户:" + name + "进行登陆,密码为：" + password);

    }
}
