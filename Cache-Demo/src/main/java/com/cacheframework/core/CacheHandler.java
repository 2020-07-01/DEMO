package com.cacheframework.core;

import com.cacheframework.annotation.Cache;
import com.cacheframework.aop.CacheAopProxyChain;
import com.cacheframework.autoload.AutoLoadHandler;
import com.cacheframework.autoload.AutoLoadTO;
import com.cacheframework.autoload.ProcessingTO;
import com.cacheframework.common.CacheUtil;
import com.cacheframework.config.AutoLoadConfig;
import com.cacheframework.dataload.DataLoader;
import com.cacheframework.lock.ILock;
import com.cacheframework.manager.ICacheManager;
import com.refresh.RefreshHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName : CacheHandler
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 处理AOP
 */
@Slf4j
@Component
public class CacheHandler {

    /**
     * 正在处理中的请求
     */
    public final ConcurrentHashMap<CacheKeyTO, ProcessingTO> processing;

    private AutoLoadConfig config;

    private ICacheManager cacheManager;

    private AutoLoadHandler autoLoadHandler;

    private final RefreshHandler refreshHandler;

    /**
     * 分布式锁
     */
    private ILock lock;


    public CacheHandler(ICacheManager cacheManager, AutoLoadConfig config) throws IllegalArgumentException {
        if (null == cacheManager) {
            throw new IllegalArgumentException("cacheManager is null");
        }

        this.processing = new ConcurrentHashMap<>(config.getProcessingMapSize());
        this.cacheManager = cacheManager;
        this.config = config;
        refreshHandler = new RefreshHandler(this, config);
    }

    /**
     * 处理@Cache
     *
     * @param cacheAopProxyChain
     * @param cache
     * @return
     */
    public Object proceed(CacheAopProxyChain cacheAopProxyChain, Cache cache) throws Throwable {

        Object[] arguments = cacheAopProxyChain.getArgs();
        CacheOpType opType = getCacheOpType(cache, arguments);
        //写操作
        if (opType == CacheOpType.WRITE) {
            return writeOnly(cacheAopProxyChain, cache);
        } else if (opType == CacheOpType.LOAD) {
            //加载操作
            return getData(cacheAopProxyChain);
        }

        Method method = cacheAopProxyChain.getMethod();
        CacheKeyTO cacheKey = getCacheKey(cacheAopProxyChain, cache);
        CacheWrapper<Object> cacheWrapper = null;
        try {
            //从缓存中获取数据
            cacheWrapper = this.get(cacheKey, method);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //只读
        if (opType == CacheOpType.READ_ONLY) {
            return null == cacheWrapper ? null : cacheWrapper.getCacheObject();
        }

        //不为空也不过期
        if (cacheWrapper != null && !cacheWrapper.isExpired()) {
            AutoLoadTO autoLoadTO = autoLoadHandler.putIfAbsent(cacheKey, cacheAopProxyChain, cache, cacheWrapper);
            //如果不为空
            if (autoLoadTO != null) {
                //刷新请求次数
                autoLoadTO.flushRequestTime(cacheWrapper);
            } else {
                //异步刷新
                refreshHandler.doRefresh(cacheAopProxyChain, cache, cacheKey, cacheWrapper);
            }
            return cacheWrapper.getCacheObject();
        }

        //没有获取到数据，加载
        DataLoader dataLoader;
        dataLoader = new DataLoader();
        long loadDataUseTime = 0L;
        boolean isFirst;
        CacheWrapper<Object> newCacheWrapper = null;
        try {
            newCacheWrapper = dataLoader.init(cacheAopProxyChain, cacheKey, cache, this).loadData().getCacheWrapper();
            loadDataUseTime = dataLoader.getLoadDataUseTime();
        } catch (Exception e) {

        } finally {
            //dataLoader的数据必须在放回对象池之前获取
            isFirst = dataLoader.isFirst();
        }

        if (isFirst) {
            AutoLoadTO autoLoadTO = autoLoadHandler.putIfAbsent(cacheKey, cacheAopProxyChain, cache, newCacheWrapper);
            try {
                writeCache(cacheAopProxyChain, cacheAopProxyChain.getArgs(), cache, cacheKey, newCacheWrapper);
                if (null != autoLoadTO) {
                    autoLoadTO.flushRequestTime(newCacheWrapper);
                    autoLoadTO.addUseTotalTime(loadDataUseTime);
                }
            } catch (Exception e) {

            }
        }
        return newCacheWrapper.getCacheObject();
    }


    private CacheKeyTO getCacheKey(CacheAopProxyChain cacheAopProxyChain, Cache cache) {
        Object target = cacheAopProxyChain.getTarget();
        String methodName = cacheAopProxyChain.getMethod().getName();
        Object[] arguments = cacheAopProxyChain.getArgs();
        String keyExpression = cache.key();
        String hfieldExpression = cache.hfield();
        return getCacheKey(target, methodName, arguments, keyExpression, hfieldExpression);
    }

    public CacheKeyTO getCacheKey(Object target, String methodName, Object[] arguments, String keyExpression, String hfieldExpression) {

        String key = null;
        String hfield = null;
        if (null != keyExpression && keyExpression.trim().length() > 0) {

        } else {
            key = CacheUtil.getDefaultCacheKey(target.getClass().getName(), methodName, arguments);
        }

        return new CacheKeyTO(config.getNamespace(), key, hfield);
    }

    /**
     * 获取缓存操作类型
     *
     * @param cache
     * @param arguments
     * @return
     */
    public CacheOpType getCacheOpType(Cache cache, Object[] arguments) {
        CacheOpType opType = cache.opType();
        if (null == opType) {
            opType = CacheOpType.READ_WRITE;
        }
        return opType;
    }


    /**
     * WRITE
     * 从数据源中读取最新的数据，并写入缓存中
     *
     * @param cacheAopProxyChain 切面(关注点被模块化为特殊的类)
     * @param cache              注解
     * @return 最新数据
     */
    public Object writeOnly(CacheAopProxyChain cacheAopProxyChain, Cache cache) throws Throwable {

        DataLoader dataLoader;

        //启用对象池
        /*if (config.isDataLoaderPooled()) {

        } else {

        }*/
        dataLoader = new DataLoader();
        CacheWrapper<Object> cacheWrapper;

        try {
            cacheWrapper = dataLoader.init(cacheAopProxyChain, cache, this).getData().getCacheWrapper();
        } catch (Exception e) {
            throw e;
        } finally {

        }

        Object result = cacheWrapper.getCacheObject();
        Object[] arguments = cacheAopProxyChain.getArgs();
        //TODO 写入缓存中


        return result;
    }

    public Object getData(CacheAopProxyChain cacheAopProxyChain) {
        return getData(cacheAopProxyChain, cacheAopProxyChain.getArgs());
    }

    public Object getData(CacheAopProxyChain cacheAopProxyChain, Object[] arguments) {

        return null;
    }

    public CacheWrapper<Object> get(CacheKeyTO key, Method method) throws IOException {
        return cacheManager.get(key, method);
    }


    public void writeCache(CacheAopProxyChain pjp, Object[] arguments, Cache cache, CacheKeyTO cacheKey,
                           CacheWrapper<Object> cacheWrappe) {

    }

    public AutoLoadConfig getAutoLoadConfig() {
        return this.config;
    }

    public ILock getLock() {
        return lock;
    }

    public void setLock(ILock lock) {
        this.lock = lock;
    }

}


