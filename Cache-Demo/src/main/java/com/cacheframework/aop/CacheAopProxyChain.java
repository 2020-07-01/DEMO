package com.cacheframework.aop;

import java.lang.reflect.Method;

/**
 * @ClassName : CacheAopProxyChain
 * @Author : yq
 * @Date: 2021-04-28
 * @Description :
 */
public interface CacheAopProxyChain {

    /**
     * 获取参数
     *
     * @return
     */
    Object[] getArgs();

    /**
     * 获取目标实例
     *
     * @return
     */
    Object getTarget();

    /**
     * 获取方法
     *
     * @return
     */
    Method getMethod();

    /**
     * 执行方法
     *
     * @param arguments
     * @return
     * @throws Throwable
     */
    Object doProxyChain(Object[] arguments) throws Throwable;


}
