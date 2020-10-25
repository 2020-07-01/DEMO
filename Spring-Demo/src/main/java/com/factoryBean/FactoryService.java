package com.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @ClassName : FactoryBeanLearn
 * @Author : yq
 * @Date: 2020-10-24
 * @Description : factoryBean可以是我们自定义他的创建过程
 */

/**
 *  此处默认的Bean name 是factoryService
 */
@Component
public class FactoryService implements FactoryBean {

    /**
     * 返回对象实例
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object getObject() {
        /**
         * 此处控制Bean的创建过程
         */
        return new BeanService();
    }

    /**
     * bean的类型
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return FactoryService.class;
    }

    /**
     * 是否是单例，默认true
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
