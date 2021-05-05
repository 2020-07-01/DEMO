package com.cacheframework.dataload;

import com.cacheframework.autoload.ProcessingTO;
import com.cacheframework.annotation.Cache;
import com.cacheframework.aop.CacheAopProxyChain;
import com.cacheframework.autoload.AutoLoadTO;
import com.cacheframework.common.LoadDataTimeOutException;
import com.cacheframework.config.AutoLoadConfig;
import com.cacheframework.core.CacheHandler;
import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import com.cacheframework.lock.ILock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import sun.awt.windows.ThemeReader;

import java.io.IOException;

/**
 * @ClassName : dataload
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 数据加载
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

    private long loadDataUseTime;

    private int tryCnt = 0;

    private DataLoader init(CacheAopProxyChain cacheAopProxyChain, AutoLoadTO autoLoadTO, CacheKeyTO cacheKey, Cache cache, CacheHandler cacheHandler) {
        this.cacheHandler = cacheHandler;
        this.cacheAopProxyChain = cacheAopProxyChain;
        this.cacheKey = cacheKey;
        this.cache = cache;
        if (null == autoLoadTO) {
            // 用户请求
            this.arguments = cacheAopProxyChain.getArgs();
        } else {
            this.arguments = autoLoadTO.getArgs();
        }
        this.loadDataUseTime = 0;
        this.tryCnt = 0;
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
     *
     * @return
     */
    public DataLoader loadData() throws Throwable {

        ProcessingTO processing = cacheHandler.processing.get(cacheKey);
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
        String threadName = Thread.currentThread().getName();
        if (isFirst) {
            if (log.isTraceEnabled()) {
                log.trace("{} first thread!", threadName);
            }
            try {
                doFirstRequest(processing);
            } catch (Exception e) {

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
     * 第一个请求从DAO加载数据
     *
     * @param processingTO
     */
    private void doFirstRequest(ProcessingTO processingTO) throws Throwable {

        ILock distributedLock = cacheHandler.getLock();
        //
        if (null != distributedLock && cache.lockExpire() > 0) {

            String lockKey = cacheKey.getLockKey();
            long startWait = processingTO.getStartTime();
            int lockState = tryLock(distributedLock, lockKey);
            if (lockState == 1) {
                try {
                    getData();
                } finally {
                    try {
                        distributedLock.unLock(lockKey);
                    } catch (Exception e) {

                    }
                }
            } else if (lockState == 0) {
                //没有获取锁，则其他线程获取了锁，正在进行数据的加载，加载完数据之后会写入到缓存
                //定时缓存尝试获取数据

                do {
                    int tryCount = 20;

                    for (int i = 0; i < tryCount; i++) {
                        cacheWrapper = cacheHandler.get(cacheKey, cacheAopProxyChain.getMethod());
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
     * @param processing
     * @param lock
     * @throws IOException
     */
    private void doWaitRequest(ProcessingTO processing, Object lock) throws Throwable {

        long startWait = processing.getStartTime();
        String tname = Thread.currentThread().getName();
        do {
            if (processing.isFirstFinished()) {
                //已完成 从本地内存获取数据，防止频繁去缓存服务器取数据，造成缓存服务器压力过大
                CacheWrapper<Object> tmpcacheWrapper = processing.getCache();

                if (null != tmpcacheWrapper) {
                    cacheWrapper = tmpcacheWrapper;
                    return;
                }

                Throwable error = processing.getError();
                //当DAO出错时，直接抛出异常
                if (null != error) {
                    //todo trance
                    if (log.isTraceEnabled()) {
                        log.trace("{} do error", tname);
                    }
                    throw error;
                }
                break;
            } else {
                //未完成
                synchronized (lock) {
                    //todo 未看懂
                    if (log.isTraceEnabled()) {
                        log.trace("{} do wait", tname);
                    }
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
            cacheWrapper = cacheHandler.get(cacheKey, cacheAopProxyChain.getMethod());
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
     * @param distributedLock
     * @param lockKey
     * @return
     */
    private int tryLock(ILock distributedLock, String lockKey) {
        try {
            return distributedLock.tryLock(lockKey, cache.lockExpire()) ? 1 : 0;
        } catch (Throwable e) {
            if (cache.openLockDown()) {
                // 关闭分布式锁
                cacheHandler.setLock(null);
                log.error("分布式锁异常，强制停止使用分布式锁!", e);
            } else {
                log.error("分布式锁异常!", e);
            }
        }
        return 2;
    }

    /**
     * 加载数据
     *
     * @return DataLoader
     */
    public DataLoader getData() throws Throwable {

        if (null != autoLoadTO) {
            autoLoadTO.setLoading(true);
        }

        long loadDataStartTime = System.currentTimeMillis();
        Object result = cacheAopProxyChain.doProxyChain(arguments);
        long loadDataUseTime = System.currentTimeMillis() - loadDataStartTime;
        AutoLoadConfig autoLoadConfig = cacheHandler.getAutoLoadConfig();
        String className = cacheAopProxyChain.getMethod().getDeclaringClass().getName();

        //TODO
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
        try {

            //todo
        } catch (Exception e) {

        }
        cacheWrapper = new CacheWrapper<>(result, expire);
    }


    public CacheWrapper<Object> getCacheWrapper() {
        if (null == cacheWrapper) {
            throw new RuntimeException("");
        }
        return cacheWrapper;
    }


    public long getLoadDataUseTime() {
        return loadDataUseTime;
    }

    public boolean isFirst() {
        return isFirst;
    }


}
