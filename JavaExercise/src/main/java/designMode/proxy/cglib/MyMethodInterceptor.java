package designMode.proxy.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName : MyMethodInterceptor
 * @Author : yq
 * @Date: 2020-11-08
 * @Description : 方法拦截器
 */
public class MyMethodInterceptor implements MethodInterceptor {

    /**
     * 如何对不同的方法进行不同的拦截和增强
     */

    /**
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("对目标类进行增强......");
        return methodProxy.invokeSuper(o, objects);
    }
}
