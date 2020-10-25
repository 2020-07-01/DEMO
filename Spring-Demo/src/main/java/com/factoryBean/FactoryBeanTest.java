package com.factoryBean;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName : FactoryBeanTest
 * @Author : yq
 * @Date: 2020-10-25
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryBeanTest {

    @Autowired
    BeanFactory beanFactory;


    @Test
    public void test(){
        /**
         * 获取Bean工厂 （容器）
         * & 在容器中获取工厂bean的对象
         * 在容器中获取实例对象
         */

        //此处在获取factoryService时获取的是FactoryBean 对象的getObject()方法返回的对象
        Object factoryBeanService = beanFactory.getBean("factoryService");
        System.out.println("factoryBean测试");
        System.out.println(factoryBeanService.getClass().getName());

    }
}
