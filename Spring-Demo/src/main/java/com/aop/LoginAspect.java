package com.aop;

import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

/**
 * @ClassName : LoginAspect
 * @Author : yq
 * @Date: 2020-11-01
 * @Description : 登录模块切面类
 */
@Component
@Aspect
public class LoginAspect {


    /**
     * 声明切点1
     */
    @Pointcut("execution(public * *(..))")
    public void pointCut() {

    }


    /**
     * 前置通知
     */
    @Before("pointCut()")
    public void executeBefore() {
        System.out.println("用户登陆前打印日志......");
    }

    /**
     * 后置通知
     */
    @After("pointCut()")
    public void executeAfter() {
        System.out.println("用户登陆后打印日志......");
    }
}
