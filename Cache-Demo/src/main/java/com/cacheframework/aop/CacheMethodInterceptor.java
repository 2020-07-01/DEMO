package com.cacheframework.aop;

import com.cacheframework.annotation.Cache;
import com.cacheframework.config.AutoloadCacheProperties;
import com.cacheframework.core.CacheHandler;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @ClassName : CacheMethodInterceptor
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 方法拦截器
 */
public class CacheMethodInterceptor implements MethodInterceptor {

    private final CacheHandler cacheHandler;

    private final AutoloadCacheProperties config;

    public CacheMethodInterceptor(CacheHandler cacheHandler, AutoloadCacheProperties config) {
        this.cacheHandler = cacheHandler;
        this.config = config;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        if (!config.isEnable()) {
            return invocation.proceed();
        }

        Method method = invocation.getMethod();

        if (method.isAnnotationPresent(Cache.class)) {
            Cache cache = method.getAnnotation(Cache.class);
            return cacheHandler.proceed(new CacheAopProxy(invocation), cache);
        } else {

        }

        return null;
    }
}
