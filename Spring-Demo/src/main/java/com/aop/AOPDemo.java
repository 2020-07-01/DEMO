package com.aop;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName : AOPDemo
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */

public class AOPDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AopConfiguration.class);
        UserDao userDao = annotationConfigApplicationContext.getBean(UserDao.class);
        userDao.query();
    }
}
