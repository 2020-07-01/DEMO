package com.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @ClassName : MyBeanFactoryPosterProcessor
 * @Author : yq
 * @Date: 2020-10-16
 * @Description :
 */
@Component
public class MyBeanFactoryPosterProcessor implements BeanFactoryPostProcessor, Ordered {


    /**
     * 在对BeanDefinition实例化之前
     * 对BeanDefinition 信息进行修改
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        //根据名称获取BeanDefinition
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userServiceImpl");
        //获取属性值
        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
        //对属性值进行改变
        mutablePropertyValues.addPropertyValue("desc", "qiang");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
