package com.cacheframework.autoload;

import com.cacheframework.common.CacheAopProxyChain;
import com.cacheframework.core.CacheHandler;
import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import com.cacheframework.annotation.Cache;
import com.cacheframework.config.AutoLoadConfig;
import com.cacheframework.dataload.DataLoader;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName : AutoLoadHandler
 * @Author : yq
 * @Date: 2021-05-04
 * @Description : 处理自动加载缓存
 * 作用：
 * 1.避免在请求高峰时，因为缓存失效，而造成数据库压力大无法承受，这部分缓存是频率高并且耗时长
 * 2.把一些耗时业务得以实现，避免缓存失效时，耗时业务造成阻塞
 * 3.读非常频繁的数据使用自动加载
 */
@Slf4j
public class AutoLoadHandler {

    public static final String THREAD_NAME_PREFIX = "autoLoadThread-";

    /**
     * 自动加载最小过期时间
     */
    public static final int AUTO_LOAD_MIN_EXPIRE = 120;

    private static final int ONE_THOUSAND_MS = 1000;

    /**
     * 自动加载队列
     */
    private final ConcurrentHashMap<CacheKeyTO, AutoLoadTO> autoLoadMap;

    private final CacheHandler cacheHandler;

    /**
     * 自动加载配置
     */
    private final AutoLoadConfig config;

    /**
     * 缓存池
     */
    private final Thread[] threads;

    private volatile boolean running = false;

    /**
     * 自动加载队列
     */
    private final LinkedBlockingQueue<AutoLoadTO> autoLoadQueue;

    /**
     * 排序进行，对自动加载队列进行排序
     */
    private final Thread sortThread;

    public AutoLoadHandler(CacheHandler cacheHandler, AutoLoadConfig config) {
        this.cacheHandler = cacheHandler;
        this.config = config;
        if (config.getThreadCnt() > 0) {
            this.running = true;
            this.autoLoadMap = new ConcurrentHashMap<>(this.config.getMaxElement());
            this.autoLoadQueue = new LinkedBlockingQueue<>(this.config.getMaxElement());
            this.threads = new Thread[this.config.getThreadCnt()];
            this.sortThread = new Thread(new SortRunnable());
            this.sortThread.start();
            this.sortThread.setDaemon(true);
            for (int i = 0; i < this.config.getThreadCnt(); i++) {
                this.threads[i] = new Thread(new AutoLoadRunnable());
                this.threads[i].setName(THREAD_NAME_PREFIX + i);
                this.threads[i].setDaemon(true);
                this.threads[i].start();
            }
        } else {
            this.threads = null;
            this.autoLoadMap = null;
            this.autoLoadQueue = null;
            this.sortThread = null;
        }
    }

    public AutoLoadTO[] getAutoLoadQueue() {
        if (null == autoLoadMap || autoLoadMap.isEmpty()) {
            return null;
        }
        AutoLoadTO[] tmpArr = new AutoLoadTO[autoLoadMap.size()];
        tmpArr = autoLoadMap.values().toArray(tmpArr);
        if (null != config.getSortType() && null != config.getSortType().getComparator()) {
            Arrays.sort(tmpArr, config.getSortType().getComparator());
        }
        return tmpArr;
    }


    /**
     * 如果不存在则直接存储 返回null
     * 如果存在则不替换，返回已存在的值
     * 如果autoLoadMap中的数据还未来得及消费，又进来一条数据
     *
     * @param cacheKey
     * @param joinPoint
     * @param cache
     * @param cacheWrapper
     * @return
     */
    public AutoLoadTO putIfAbsent(CacheKeyTO cacheKey, CacheAopProxyChain joinPoint, Cache cache,
                                  CacheWrapper<Object> cacheWrapper) {
        if (null == autoLoadMap) {
            return null;
        }

        if (cacheWrapper == null) {
            return null;
        }

        AutoLoadTO autoLoadTO = autoLoadMap.get(cacheKey);
        if (autoLoadTO != null) {
            //已经存在
            return autoLoadTO;
        }
        //TODO 表达式解析忽略
        int expire = cacheWrapper.getExpire();
        if (expire >= AUTO_LOAD_MIN_EXPIRE && autoLoadMap.size() < this.config.getMaxElement()) {
            Object[] arguments;

            //TODO 深度复制忽略
            arguments = joinPoint.getArgs();
            autoLoadTO = new AutoLoadTO(cacheKey, joinPoint, arguments, cache, expire);
            AutoLoadTO tmp = autoLoadMap.putIfAbsent(cacheKey, autoLoadTO);
            if (null == tmp) {
                return autoLoadTO;
            } else {
                return tmp;
            }
        }
        return null;
    }


    /**
     * 写缓存并设置上一次加载时间
     *
     * @param cache
     * @param cacheAopProxyChain
     * @param cacheKey
     * @param newCacheWrapper
     * @param loadDataUseTime
     * @param autoLoadTO
     */
    private void writeCacheAndSetLoadTime(Cache cache, CacheAopProxyChain cacheAopProxyChain, CacheKeyTO cacheKey, CacheWrapper<Object> newCacheWrapper, long loadDataUseTime, AutoLoadTO autoLoadTO) {

        try {
            if (null != newCacheWrapper) {
                cacheHandler.writeCache(cacheAopProxyChain, autoLoadTO.getArgs(), cache, cacheKey, newCacheWrapper);
                autoLoadTO.setLastLoadTime(newCacheWrapper.getLastLoadTime())
                        .setExpire(newCacheWrapper.getExpire()).addUseTotalTime(loadDataUseTime);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 自动加载队列消费者
     */
    class AutoLoadRunnable implements Runnable {

        @Override
        public void run() {
            while (running) {
                try {
                    AutoLoadTO tmpTo = autoLoadQueue.take();
                    if (tmpTo != null) {
                        loadCache(tmpTo);
                        Thread.sleep(config.getAutoLoadPeriod());
                    }
                } catch (Throwable e) {
                    log.error(e.getMessage(), e);
                }

            }
        }

        /**
         * 自动加载数据
         *
         * @param autoLoadTO 自动加载数据类
         */
        private void loadCache(AutoLoadTO autoLoadTO) throws Throwable {
            if (null == autoLoadTO || autoLoadMap == null) {
                return;
            }
            long now = System.currentTimeMillis();
            if (autoLoadTO.getLastRequestTime() <= 0 || autoLoadTO.getLastLoadTime() <= 0) {
                //上次请求数据的时间，最后加载时间
                return;
            }
            Cache cache = autoLoadTO.getCache();
            long requestTimeout = cache.requestTimeout();
            boolean alwaysCache = cache.alwaysCache();
            //如果超过一定时间没有请求数据，则从队列中删除，
            if (!alwaysCache && requestTimeout > 0 && (now - autoLoadTO.getLastRequestTime()) >= requestTimeout) {
                autoLoadMap.remove(autoLoadTO.getCacheKey());
                return;
            }

            //频率高 耗时少，不需要进行自动加载
            if (!alwaysCache && autoLoadTO.getLoadCount() > 100 && autoLoadTO.getAverageUseTime() < config.getLoadUseTimeForAutoLoad1()) {
                autoLoadMap.remove(autoLoadTO.getCacheKey());
                return;
            }

            //对于使用频率很低的数据，不自动加载
            long difFirstRequestTime = now - autoLoadTO.getFirstRequestTime();
            //一小时
            long nowHourSecs = 3600000L;
            if (!alwaysCache && difFirstRequestTime > nowHourSecs && autoLoadTO.getAverageUseTime() < config.getLoadUseTimeForAutoLoad2()) {
                autoLoadMap.remove(autoLoadTO.getCacheKey());
                return;
            }

            if (autoLoadTO.isLoading()) {
                return;
            }

            int expire = autoLoadTO.getExpire();
            //过期时间太短，不自动加载
            if (!alwaysCache && expire < AUTO_LOAD_MIN_EXPIRE) {
                return;
            }
            //预警自动刷新时间 ，如果在此时间内将会过期，则刷新
            int alarmTime = autoLoadTO.getCache().alarmTime();
            long timeout;
            if (alarmTime > 0 && alarmTime < expire) {
                timeout = expire - alarmTime;
            } else {
                if (expire > 600) {
                    timeout = expire - 120;
                } else {
                    timeout = expire - 60;
                }
            }

            int rand = ThreadLocalRandom.current().nextInt(10);
            timeout = (timeout + (rand % 2 == 0 ? rand : -rand)) * ONE_THOUSAND_MS;

            if ((now - autoLoadTO.getLastLoadTime()) < timeout) {
                return;
            }

            CacheWrapper<Object> result = null;
            //加载之前先check缓存
            if (config.isCheckFromCacheBeforeLoad()) {
                try {
                    Method method = autoLoadTO.getJoinPoint().getMethod();
                    //从缓存中获取数据
                    result = cacheHandler.get(autoLoadTO.getCacheKey(), method);
                } catch (Exception e) {

                }
                //如果已经被别的服务器更新了，则不需要再次更新
                if (null != result) {
                    //重新设置缓存时间
                    autoLoadTO.setExpire(result.getExpire());
                    if (result.getLastLoadTime() > autoLoadTO.getLastLoadTime() && (now - result.getLastLoadTime()) < timeout) {
                        autoLoadTO.setLastLoadTime(result.getLastLoadTime());
                        return;
                    }
                }
            }

            //TODO dataloader 池
            CacheAopProxyChain cacheAopProxyChain = autoLoadTO.getJoinPoint();
            CacheKeyTO cacheKey = autoLoadTO.getCacheKey();
            DataLoader dataLoader;
            dataLoader = new DataLoader();
            CacheWrapper<Object> newCacheWrapper = null;
            boolean isFirst = false;
            long loadDataUseTime = 0L;
            try {
                newCacheWrapper = dataLoader.init(cacheAopProxyChain, cache, cacheHandler).getData().getCacheWrapper();
                loadDataUseTime = dataLoader.getLoadDataUseTime();
            } catch (Exception e) {

            } finally {

                // dataLoader 的数据必须在放回对象池之前获取
                isFirst = dataLoader.isFirst();
            }

            if (isFirst) {
                //如果加载失败，则把就数据进行续租
                if (null == newCacheWrapper && null != result) {
                    int newExpire = !alwaysCache ? AUTO_LOAD_MIN_EXPIRE + 60 : cache.expire();
                    newCacheWrapper = new CacheWrapper<>(result.getCacheObject(), newExpire);
                }
                writeCacheAndSetLoadTime(cache, cacheAopProxyChain, cacheKey, newCacheWrapper, loadDataUseTime, autoLoadTO);
            }
        }
    }

    class SortRunnable implements Runnable {

        @Override
        public void run() {
            while (running) {
                int sleep = 100;

                if (autoLoadMap.isEmpty() || autoLoadQueue.size() > 0) {
                    try {
                        Thread.sleep(sleep);
                    } catch (Exception e) {
                        //TODO
                        log.error("");
                    }
                }

                //TODO

                AutoLoadTO[] tmpArr = getAutoLoadQueue();
                if (null == tmpArr || tmpArr.length == 0) {
                    continue;
                }
                for (int i = 0; i < tmpArr.length; i++) {
                    try {
                        AutoLoadTO to = tmpArr[i];
                        autoLoadQueue.put(to);
                        if (i > 0 & i % 1000 == 0) {
                            //TODO
                            Thread.yield();
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

}
