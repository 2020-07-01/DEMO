package com.cacheframework.core;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : CacheKeyTO
 * @Author : yq
 * @Date: 2021-04-27
 * @Description : 处理自动加载数据到缓存
 */
@Data
public class CacheKeyTO implements Serializable {

    private final String namespace;
    /**
     * 缓存key
     */
    private final String key;

    /**
     * 设置哈希表中的字段
     * 如果设置此项，则用哈希表进行存储
     */
    private final String hfield;

    public CacheKeyTO(String namespace, String key, String hfield) {

        this.namespace = namespace;
        this.key = key;
        this.hfield = hfield;
    }

    public String getLockKey() {
        StringBuilder key = new StringBuilder(getKey());
        if (null != hfield && hfield.length() > 0) {
            key.append(":").append(hfield);
        }
        key.append(":com.lock");
        return key.toString();
    }

    public String getCacheKey() {
        if (null != this.namespace && this.namespace.length() > 0) {
            return new StringBuilder(this.namespace).append(":").append(this.key).toString();
        }
        return this.key;
    }

}
