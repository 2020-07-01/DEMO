package com.cacheframework.dataload;

import com.cacheframework.autoload.ProcessingTO;
import com.cacheframework.annotation.Cache;
import com.cacheframework.common.CacheAopProxyChain;
import com.cacheframework.autoload.AutoLoadTO;
import com.cacheframework.common.LoadDataTimeOutException;
import com.cacheframework.config.AutoLoadConfig;
import com.cacheframework.core.CacheHandler;
import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @ClassName : DataLoader
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 数据加载
 * 等待机制
 */
@Slf4j
public class DataLoader {

    private CacheHandler cacheHandler;

    private CacheAopProxyChain cacheAopProxyChain;

    private CacheKeyTO cacheKey;

    private Cache cache;

    private Object[] arguments;

    private boolean isFirst;

    private AutoLoadTO autoLoadTO;

    private CacheWrapper<Object> cacheWrapper;
    /**
     * 加载所用时间
     */
    private long loadDataUseTime;

    /**
     * 加载数据重试次数
     */
    private int tryCnt = 0;

    private DataLoader init(CacheAopProxyChain cacheAopProxyChain, AutoLoadTO autoLoadTO, CacheKeyTO cacheKey, Cache cache, CacheHandler cacheHandler) {
        this.cacheHandler = cacheHandler;
        this.cacheAopProxyChain = cacheAopProxyChain;
        this.cacheKey = cacheKey;
        this.cache = cache;
        this.autoLoadTO = autoLoadTO;
        if (null == autoLoadTO) {
            // 用户请求
            this.arguments = cacheAopProxyChain.getArgs();
        } else {
            // 来自AutoLoadHandler的请求
            this.arguments = autoLoadTO.getArgs();
        }
        this.tryCnt = 0;
        this.loadDataUseTime = 0;
        return this;
    }


    public DataLoader init(CacheAopProxyChain cacheAopProxyChain, Cache cache, CacheHandler cacheHandler) {
        return init(cacheAopProxyChain, null, null, cache, cacheHandler);
    }

    public DataLoader init(CacheAopProxyChain cacheAopProxyChain, CacheKeyTO cacheKeyTO, Cache cache, CacheHandler cacheHandler) {
        return init(cacheAopProxyChain, null, cacheKey, cache, cacheHandler);
    }

    /**
     * 从DAO加载数据
     * 第一个请求从DAO加载数据
     * 后续请求等待第一个请求执行成功后从缓存中获取数据
     *
     * @return dataLoader
     */
    public DataLoader loadData() throws Throwable {

        ProcessingTO processing = cacheHandler.getProcessing().get(cacheKey);
        if (processing == null) {
            ProcessingTO newProcessing = new ProcessingTO();
            //减少dao层并发量，增加等待机制
            ProcessingTO firstProcessing = cacheHandler.processing.putIfAbsent(cacheKey, newProcessing);
            if (firstProcessing == null) {
                //直接添加,返回null 第一次
                isFirst = true;
                processing = newProcessing;
            } else {
                //已经存在，未添加
                isFirst = false;
                // 获取到第一个线程的ProcessingTO 的引用，保证所有请求都指向同一个引用
                processing = firstProcessing;
            }
        } else {
            isFirst = false;
        }
        Object lock = processing;
        if (isFirst) {
            try {
                doFirstRequest(processing);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                processing.setFirstFinished(true);
                cacheHandler.processing.remove(cacheKey);
                //todo 为什么
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        } else {
            doWaitRequest(processing, lock);
        }
        return this;
    }


    /**
     * 对当前cacheKey的第一此请求
     *
     * @param processingTO 请求包装类
     */
    private void doFirstRequest(ProcessingTO processingTO) throws Throwable {

        //ILock distributedLock = cacheHandler.getLock();

        if ( cache.lockExpire() > 0) {
            String lockKey = cacheKey.getLockKey();
            long startWait = processingTO.getStartTime();
            boolean lock = tryLock("", lockKey);
            if (lock) {
                try {
                    getData();
                } finally {
                    try {
                        //todo 分布式锁key 与 value
                        //distributedLock.releaseLock(lockKey, "");
                    } catch (Exception e) {
                        log.error("release com.lock failure!", e);
                    }
                }
            } else {
                //没有获取锁，则其他线程获取了锁，正在进行数据的加载，加载完数据之后会写入到缓存
                //定时缓存尝试获取数据
                do {
                    int tryCount = 20;
                    for (int i = 0; i < tryCount; i++) {
                        cacheWrapper = cacheHandler.getCache(cacheKey, cacheAopProxyChain.getMethod());
                        if (null != cacheWrapper) {
                            break;
                        }
                        Thread.sleep(10);
                    }
                    if (null != cacheWrapper) {
                        break;
                    }
                } while (System.currentTimeMillis() - startWait < cache.waitTimeOut());
            }
        }
        if (null == cacheWrapper) {
            //强制从DAO中加载数据
            getData();

        }
        processingTO.setCache(cacheWrapper);
    }

    /**
     * 处理等待请求
     *
     * @param processing 请求包装类
     * @param lock       com.lock
     * @throws IOException IOException
     */
    private void doWaitRequest(ProcessingTO processing, Object lock) throws Throwable {
        long startWait = processing.getStartTime();
        do {
            if (processing.isFirstFinished()) {
                //已完成 从本地内存获取数据，防止频繁去缓存服务器取数据，造成缓存服务器压力过大
                CacheWrapper<Object> tempCacheWrapper = processing.getCache();
                if (null != tempCacheWrapper) {
                    cacheWrapper = tempCacheWrapper;
                    return;
                }
                break;
            } else {
                //未完成
                synchronized (lock) {
                    //todo 未看懂
                    try {
                        // 如果要测试lock对象是否有效，wait时间去掉就可以
                        lock.wait(2);
                    } catch (InterruptedException ex) {
                        log.error(ex.getMessage(), ex);
                    }
                }
            }
        } while (System.currentTimeMillis() - startWait < cache.waitTimeOut());
        if (null == cacheWrapper) {
            cacheWrapper = cacheHandler.getCache(cacheKey, cacheAopProxyChain.getMethod());
        }
        if (null == cacheWrapper) {
            AutoLoadConfig config = cacheHandler.getAutoLoadConfig();
            if (tryCnt < config.getLoadDataTryCnt()) {
                tryCnt++;
                loadData();
            } else {
                throw new LoadDataTimeOutException("cache for key \"" + cacheKey.getCacheKey() + "\" loaded " + tryCnt + " times.");
            }
        }
    }

    /**
     * 获取分布式锁
     * 此处设计锁降级
     *
     * @param distributedLock distributedLock
     * @param lockKey         lockKey
     * @return boolean 是否获取到锁
     */
    private boolean tryLock(Object distributedLock, String lockKey) {
        try {
            return false;
            //return distributedLock.tryLock(lockKey, cache.lockExpire());
        } catch (Throwable e) {
            if (cache.openLockDown()) {
                //cacheHandler.setLock(null);
                log.error("分布式锁异常，强制停止使用分布式锁!", e);
            } else {
                log.error("分布式锁异常!", e);
            }
        }
        return false;
    }

    /**
     * 直接从DAO获取数据
     *
     * @return DataLoader
     */
    public DataLoader getData()  {
        long loadDataStartTime = System.currentTimeMillis();
        //执行dao层方法加载数据
        Object result = cacheAopProxyChain.doProxyChain(arguments);
        loadDataUseTime = System.currentTimeMillis() - loadDataStartTime;
        //todo 加载时间计算
        buildCacheWrapper(result);
        return this;
    }

    /**
     * 构建缓存数据包装类
     *
     * @param result DAO 层结果
     */
    private void buildCacheWrapper(Object result) {
        int expire = cache.expire();
        cacheWrapper = new CacheWrapper<>(result, expire);
    }

    public CacheWrapper<Object> getCacheWrapper() {
        if (null == cacheWrapper) {
            throw new RuntimeException("");
        }
        return cacheWrapper;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public long getLoadDataUseTime() {
        return loadDataUseTime;
    }


}
