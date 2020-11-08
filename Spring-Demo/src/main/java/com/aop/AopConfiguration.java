package com.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName : Config
 * @Author : yq
 * @Date: 2020-11-01
 * @Description : aop配置类
 */
@Configuration
@ComponentScan(basePackages = "com.aop")
@EnableAspectJAutoProxy
public class AopConfiguration {

}
