package com.cacheframework.refresh;

import com.cacheframework.annotation.Cache;
import com.cacheframework.common.CacheAopProxyChain;
import com.cacheframework.config.AutoLoadConfig;
import com.cacheframework.core.CacheHandler;
import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import com.cacheframework.dataload.DataLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : RefreshHandler
 * @Author : yq
 * @Date: 2021-05-05
 * @Description : 异步刷新缓存处理器
 */
@Slf4j
public class RefreshHandler {

    private static final int REFRESH_MIN_EXPIRE = 120;

    private static final int ONE_THOUSAND_MS = 1000;

    private final ConcurrentHashMap<CacheKeyTO, Byte> refreshing;

    /**
     * 刷新缓存线程池
     */
    private final ThreadPoolExecutor refreshThreadPool;

    private final CacheHandler cacheHandler;

    public RefreshHandler(CacheHandler cacheHandler, AutoLoadConfig config) {
        this.cacheHandler = cacheHandler;
        //todo 创建线程池
        int maximumPoolSize = 10;
        int keepAliveTime = 10;
        TimeUnit unit = TimeUnit.MINUTES;
        int queueCapacity = 1000;
        refreshing = new ConcurrentHashMap<>(queueCapacity);
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(queueCapacity);
        RejectedExecutionHandler rejectedHandler = new RefreshRejectedExecutionHandler();
        refreshThreadPool = new ThreadPoolExecutor(10, maximumPoolSize, keepAliveTime, unit, queue,
                new ThreadFactory() {

                    private final AtomicInteger threadNumber = new AtomicInteger(1);

                    private final String namePrefix = "autoload-cache-RefreshHandler-";

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
                        t.setDaemon(true);
                        return t;
                    }
                }, rejectedHandler);
    }

    public void doRefresh(CacheAopProxyChain cacheAopProxyChain, Cache cache, CacheKeyTO cacheKey, CacheWrapper<Object> cacheWrapper) {

        int expire = cacheWrapper.getExpire();
        //过期时间太小，不允许自动加载，避免过于频繁，影响系统稳定性
        if (expire < REFRESH_MIN_EXPIRE) {
            return;
        }
        //预警刷新时间,时间内过期将会刷新
        int alarmTime = cache.alarmTime();
        //超时时间
        long timeOut;
        if (alarmTime > 0 && alarmTime < expire) {
            timeOut = expire - alarmTime;
        } else {
            if (expire >= 600) {
                timeOut = expire - REFRESH_MIN_EXPIRE;
            } else {
                timeOut = expire - 60;
            }
        }

        if ((System.currentTimeMillis() - cacheWrapper.getLastLoadTime()) < (timeOut * ONE_THOUSAND_MS)) {
            return;
        }

        Byte tmpByte = refreshing.get(cacheKey);
        //如果有正在刷新的请求则不处理
        if (null != tmpByte) {
            return;
        }

        tmpByte = 1;
        if (null == refreshing.putIfAbsent(cacheKey, tmpByte)) {
            try {
                refreshThreadPool.execute(new RefreshTask(cacheAopProxyChain, cache, cacheKey, cacheWrapper));
            } catch (Exception e) {

            }
        }
    }

    class RefreshTask implements Runnable {

        private final CacheAopProxyChain cacheAopProxyChain;

        private final Cache cache;

        private final CacheKeyTO cacheKey;

        private final CacheWrapper<Object> cacheWrapper;

        private final Object[] arguments;

        public RefreshTask(CacheAopProxyChain cacheAopProxyChain, Cache cache, CacheKeyTO cacheKey, CacheWrapper<Object> cacheWrapper)
                throws Exception {
            this.cacheAopProxyChain = cacheAopProxyChain;
            this.cache = cache;
            this.cacheKey = cacheKey;
            this.cacheWrapper = cacheWrapper;
            //TODO 深度复制
            this.arguments = cacheAopProxyChain.getArgs();
        }

        @Override
        public void run() {
            DataLoader dataLoader;
            //todo
            dataLoader = new DataLoader();

            boolean isFirst = false;
            CacheWrapper<Object> newCacheWrapper = null;
            try {
                newCacheWrapper = dataLoader.init(cacheAopProxyChain, cache, cacheHandler).getData().getCacheWrapper();
            } catch (Throwable e) {

            } finally {
                isFirst = dataLoader.isFirst();
            }

            if (isFirst) {
                // 如果数据加载失败，则把旧数据进行续租
                if (null == newCacheWrapper && null != cacheWrapper) {
                    int newExpire = cacheWrapper.getExpire() / 2;
                    if (newExpire < 120) {
                        newExpire = 120;
                    }
                    newCacheWrapper = new CacheWrapper<Object>(cacheWrapper.getCacheObject(), newExpire);
                }

                try {
                    if (null != newCacheWrapper) {
                        //将数据写入缓存
                        cacheHandler.writeCache(cacheAopProxyChain, arguments, cache, cacheKey, newCacheWrapper);
                    }
                } catch (Throwable e) {
                    log.error(e.getMessage(), e);
                }
            }
            refreshing.remove(cacheKey);
        }

        public CacheKeyTO getCacheKey() {
            return cacheKey;
        }
    }

    class RefreshRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                Runnable last = e.getQueue().poll();
                if (last instanceof RefreshTask) {
                    RefreshTask lastTask = (RefreshTask) last;
                    refreshing.remove(lastTask.getCacheKey());
                }
                e.execute(r);
            }
        }

    }

}
