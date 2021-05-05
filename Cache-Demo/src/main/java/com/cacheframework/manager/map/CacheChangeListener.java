package com.cacheframework.manager.map;

/**
 * @ClassName : CacheChangeListener
 * @Author : yq
 * @Date: 2021-04-28
 * @Description : 缓存变更监听器
 */
public interface CacheChangeListener {

    /**
     * 变更一条记录
     */
    void cacheChange();

    /**
     * 变更多条记录
     *
     * @param count count
     */
    void cacheChange(int count);

}
