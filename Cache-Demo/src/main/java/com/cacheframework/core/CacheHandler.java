package com.cacheframework.core;

import com.cacheframework.annotation.Cache;
import com.cacheframework.common.CacheAopProxyChain;
import com.cacheframework.autoload.AutoLoadHandler;
import com.cacheframework.autoload.AutoLoadTO;
import com.cacheframework.autoload.ProcessingTO;
import com.cacheframework.common.CacheUtil;
import com.cacheframework.common.MSetParam;
import com.cacheframework.config.AutoLoadConfig;
import com.cacheframework.refresh.RefreshHandler;
import com.cacheframework.dataload.DataLoader;
import com.cacheframework.lock.ILock;
import com.cacheframework.manager.ICacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
     * 当缓存失效时，如果有多个请求进来，先入队列
     * 正在处理中的请求
     */
    public final ConcurrentHashMap<CacheKeyTO, ProcessingTO> processing;

    /**
     * 自动加载配置
     */
    private final AutoLoadConfig autoLoadConfig;

    /**
     * 缓存管理接口
     */
    private final ICacheManager cacheManager;

    /**
     * 自动加载处理
     */
    private final AutoLoadHandler autoLoadHandler;

    /**
     * 刷新处理
     */
    private final RefreshHandler refreshHandler;

    /**
     * 分布式锁
     */
    private ILock lock;

    public CacheHandler(ICacheManager cacheManager, AutoLoadConfig autoLoadConfig) throws IllegalArgumentException {
        if (null == cacheManager) {
            throw new IllegalArgumentException("cacheManager is null");
        }

        this.processing = new ConcurrentHashMap<>(autoLoadConfig.getProcessingMapSize());
        this.cacheManager = cacheManager;
        this.autoLoadConfig = autoLoadConfig;
        this.autoLoadHandler = new AutoLoadHandler(this, autoLoadConfig);
        refreshHandler = new RefreshHandler(this, autoLoadConfig);
    }

    /**
     * 处理@Cache
     *
     * @param cacheAopProxyChain 切面
     * @param cache              注解
     * @return T 返回值
     */
    public Object proceed(CacheAopProxyChain cacheAopProxyChain, Cache cache) throws Throwable {

        CacheOpType opType = getCacheOpType(cache);
        //写操作
        if (opType == CacheOpType.WRITE) {
            return writeOnly(cacheAopProxyChain, cache);
        } else if (opType == CacheOpType.LOAD) {
            //加载操作
            return getData(cacheAopProxyChain);
        }

        Method method = cacheAopProxyChain.getMethod();
        CacheKeyTO cacheKey = getCacheKey(cacheAopProxyChain, cache);
        if (cacheKey == null) {
            //加载数据
            return getData(cacheAopProxyChain);
        }
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
        //TODO 此处考虑对象池
        DataLoader dataLoader = new DataLoader();
        long loadDataUseTime = 0L;
        boolean isFirst;
        CacheWrapper<Object> newCacheWrapper = null;
        try {
            newCacheWrapper = dataLoader.init(cacheAopProxyChain, cacheKey, cache, this).loadData().getCacheWrapper();
            loadDataUseTime = dataLoader.getLoadDataUseTime();
        } catch (Exception e) {
            log.error("");
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
                log.error("write cache error ", e);
            }
        }
        return newCacheWrapper == null ? null : newCacheWrapper.getCacheObject();
    }


    private CacheKeyTO getCacheKey(CacheAopProxyChain cacheAopProxyChain, Cache cache, Object result) {

        Object target = cacheAopProxyChain.getTarget();
        String methodName = cacheAopProxyChain.getMethod().getName();
        Object[] arguments = cacheAopProxyChain.getArgs();
        String key = cache.key();
        String hfield = cache.hfield();
        return getCacheKey(target, methodName, arguments, key, hfield, result, true);
    }

    /**
     * 生成缓存key
     *
     * @param cacheAopProxyChain cacheAopProxyChain
     * @param cache              cache
     * @return CacheKeyTO
     */
    private CacheKeyTO getCacheKey(CacheAopProxyChain cacheAopProxyChain, Cache cache) {
        Object target = cacheAopProxyChain.getTarget();
        String methodName = cacheAopProxyChain.getMethod().getName();
        Object[] arguments = cacheAopProxyChain.getArgs();
        String keyExpression = cache.key();
        String hfieldExpression = cache.hfield();
        return getCacheKey(target, methodName, arguments, keyExpression, hfieldExpression, null, true);
    }

    /**
     * 生成缓存KeyTO
     *
     * @param target           类
     * @param methodName       方法名
     * @param arguments        参数
     * @param keyExpression    key
     * @param hfieldExpression hfield(Hash 类型)
     * @param result           执行实际方法的返回值
     * @param hasRetVal        是否有返回值
     * @return CacheKeyTO
     */
    public CacheKeyTO getCacheKey(Object target, String methodName, Object[] arguments, String keyExpression,
                                  String hfieldExpression, Object result, boolean hasRetVal) {
        String key = null;
        String hfield = hfieldExpression;
        //todo hfied
        key = CacheUtil.getDefaultCacheKey(target.getClass().getName(), methodName, arguments);
        return new CacheKeyTO(autoLoadConfig.getNamespace(), key, hfield);
    }

    /**
     * 获取缓存操作类型
     *
     * @param cache cache
     * @return CacheOpType
     */
    public CacheOpType getCacheOpType(Cache cache) {
        //todo 事务
        return cache.opType();
    }


    /**
     * WRITE
     * 1.从DAO加载最新的数据
     * 2.写入缓存
     *
     * @param cacheAopProxyChain 切面(关注点被模块化为特殊的类)
     * @param cache              注解
     * @return 最新数据
     */
    public Object writeOnly(CacheAopProxyChain cacheAopProxyChain, Cache cache) throws Throwable {

        DataLoader dataLoader = new DataLoader();
        CacheWrapper<Object> cacheWrapper;
        //初始化并加载加载数据
        cacheWrapper = dataLoader.init(cacheAopProxyChain, cache, this).getData().getCacheWrapper();
        Object result = cacheWrapper.getCacheObject();
        Object[] arguments = cacheAopProxyChain.getArgs();
        CacheKeyTO cacheKey = getCacheKey(cacheAopProxyChain, cache, result);
        //写入缓存
        writeCache(cacheAopProxyChain, arguments, cache, cacheKey, cacheWrapper);
        return result;
    }

    /**
     * 加载数据
     *
     * @param cacheAopProxyChain cacheAopProxyChain
     * @return DAO层数据
     */
    public Object getData(CacheAopProxyChain cacheAopProxyChain) throws Throwable {
        return getData(cacheAopProxyChain, cacheAopProxyChain.getArgs());
    }

    public Object getData(CacheAopProxyChain cacheAopProxyChain, Object[] arguments) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            Object result = cacheAopProxyChain.doProxyChain(arguments);
            long useTime = System.currentTimeMillis() - startTime;
            if (autoLoadConfig.isPrintSlowLog() && useTime >= autoLoadConfig.getSlowLoadTime()) {
                String className = cacheAopProxyChain.getTarget().getClass().getName();
                if (log.isWarnEnabled()) {
                    log.warn("{}.{}, use time:{}ms", className, cacheAopProxyChain.getMethod().getName(), useTime);
                }
            }
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public CacheWrapper<Object> get(CacheKeyTO key, Method method) throws IOException {
        return cacheManager.get(key, method);
    }

    /**
     * 将数据写入缓存
     *
     * @param pjp          pjp
     * @param arguments    arguments
     * @param cache        cache
     * @param cacheKeyTO   cacheKeyTO
     * @param cacheWrapper cacheWrapper
     */
    public void writeCache(CacheAopProxyChain pjp, Object[] arguments, Cache cache, CacheKeyTO cacheKeyTO,
                           CacheWrapper<Object> cacheWrapper) {
        if (cacheKeyTO == null) {
            return;
        }
        Method method = pjp.getMethod();
        List<MSetParam> params = new LinkedList<>();
        if (cacheWrapper.getExpire() > 0) {
            params.add(new MSetParam(cacheKeyTO, cacheWrapper));
        }
        int size = params.size();
        if (size == 1) {
            //String 类型
            MSetParam param = params.get(0);
            this.setCache(param.getCacheKey(), param.getResult(), method);
        } else {
            //set 类型
            this.mset(method, params);
        }
    }

    /**
     * 往缓存写数据
     *
     * @param cacheKeyTO   缓存key
     * @param cacheWrapper 缓存值
     * @param method       method
     */
    public void setCache(CacheKeyTO cacheKeyTO, CacheWrapper<Object> cacheWrapper, Method method) {
        cacheManager.setCache(cacheKeyTO, cacheWrapper, method);
    }

    /**
     * 往缓存写数据
     *
     * @param method method
     * @param params params
     */
    public void mset(final Method method, final Collection<MSetParam> params) {
        cacheManager.mset(method, params);
    }

    public AutoLoadConfig getAutoLoadConfig() {
        return this.autoLoadConfig;
    }

    public ILock getLock() {
        return lock;
    }

    public void setLock(ILock lock) {
        this.lock = lock;
    }

    public ConcurrentHashMap<CacheKeyTO, ProcessingTO> getProcessing() {
        return processing;
    }
}


