package com.cacheframework.common;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @ClassName : CacheAopProxy
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 缓存切面代理类
 */
public class CacheAopProxy implements CacheAopProxyChain {

    private final MethodInvocation invocation;

    private Method method;

    public CacheAopProxy(MethodInvocation invocation) {
        this.invocation = invocation;
    }

    /**
     * 获取参数
     *
     * @return
     */
    @Override
    public Object[] getArgs() {
        return invocation.getArguments();
    }

    /**
     * 获取目标实例
     *
     * @return
     */
    @Override
    public Object getTarget() {
        if (null == method) {
            this.method = invocation.getMethod();
        }
        return method;
    }

    /**
     * 获取方法
     *
     * @return
     */
    @Override
    public Method getMethod() {
        return method;
    }

    /**
     * 执行方法
     *
     * @param arguments
     * @return
     * @throws Throwable
     */
    @Override
    public Object doProxyChain(Object[] arguments) throws Throwable {
        return getMethod().invoke(invocation.getThis(), arguments);
    }
}
