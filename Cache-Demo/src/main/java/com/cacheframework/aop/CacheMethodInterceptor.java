package com.cacheframework.aop;

import com.cacheframework.annotation.Cache;
import com.cacheframework.common.CacheAopProxy;
import com.cacheframework.config.AutoloadCacheProperties;
import com.cacheframework.core.CacheHandler;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;

/**
 * @ClassName : CacheMethodInterceptor
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 方法拦截器
 */
public class CacheMethodInterceptor implements MethodInterceptor {

    private final CacheHandler cacheHandler;

    private final AutoloadCacheProperties autoLoadCacheConfig;

    public CacheMethodInterceptor(CacheHandler cacheHandler, AutoloadCacheProperties autoLoadCacheConfig) {
        this.cacheHandler = cacheHandler;
        this.autoLoadCacheConfig = autoLoadCacheConfig;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        if (!autoLoadCacheConfig.isEnable()) {
            return invocation.proceed();
        }

        Method method = invocation.getMethod();
        if (method.isAnnotationPresent(Cache.class)) {
            Cache cache = method.getAnnotation(Cache.class);
            return cacheHandler.proceed(new CacheAopProxy(invocation), cache);
        } else {
            //todo AopUtils
            //根据代理对象上的方法找到真实对象上的方法
            Method specificMethod = AopUtils.getMostSpecificMethod(method, invocation.getThis().getClass());
            Cache cache = method.getAnnotation(Cache.class);
            if (specificMethod.isAnnotationPresent(Cache.class)) {
                return cacheHandler.proceed(new CacheAopProxy(invocation), cache);
            }
        }
        return invocation.proceed();
    }
}
