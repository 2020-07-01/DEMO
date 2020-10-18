package com.service.impl;

import com.service.HelloService;

/**
 * @ClassName : HelloServiceImpl1
 * @Author : yq
 * @Date: 2020-10-18
 * @Description :
 */
public class HelloServiceImpl1 implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl1");
    }
}
