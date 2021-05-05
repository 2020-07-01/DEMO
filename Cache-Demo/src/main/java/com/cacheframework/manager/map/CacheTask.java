package com.cacheframework.manager.map;

/**
 * @ClassName : CacheTask
 * @Author : yq
 * @Date: 2021-04-28
 * @Description :
 */
public class CacheTask implements Runnable, CacheChangeListener {

    private MapCacheManager cacheManager;

    public CacheTask(MapCacheManager cacheManager){
        this.cacheManager = cacheManager;
    }

    @Override
    public void run() {

    }

    /**
     * 变更一条记录
     */
    @Override
    public void cacheChange() {

    }

    /**
     * 变更多条记录
     *
     * @param count count
     */
    @Override
    public void cacheChange(int count) {

    }
}
