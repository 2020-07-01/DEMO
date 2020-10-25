package com.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @ClassName : MyBeanPosterProcessor
 * @Author : yq
 * @Date: 2020-10-18
 * @Description :BeanPostProcessor
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 后处理器，在Bean 初始化前后进行一些处理工作
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        //System.out.println("postProcessBeforeInitialization..." + beanName + "=>" + bean);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       // System.out.println("postProcessAfterInitialization..." + beanName + "=>" + bean);
        return bean;
    }


}
