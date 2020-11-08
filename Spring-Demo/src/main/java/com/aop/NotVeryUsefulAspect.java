package com.aop;

import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName : NotVeryUsefulAspect
 * @Author : yq
 * @Date: 2020-11-01
 * @Description :
 */

@Component
@Aspect
public class NotVeryUsefulAspect {


    /**
     * 声明切点1
     */
    @Pointcut("execution(public * *(..))")
    public void pointCut1() {

    }


    @Before("pointCut1()")
    public void executeBefore() {
        System.out.println("before......");
    }
}
