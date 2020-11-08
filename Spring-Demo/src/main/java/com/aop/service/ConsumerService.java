package com.aop.service;


/**
 * @ClassName : UserDao
 * @Author : yq
 * @Date: 2020-11-01
 * @Description : 用户
 */
public interface ConsumerService {

    /**
     * 用户登录
     * @param name
     * @param password
     */
    void login(String name,String password);
}
