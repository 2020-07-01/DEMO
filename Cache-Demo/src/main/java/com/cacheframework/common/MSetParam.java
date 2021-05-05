package com.cacheframework.common;

import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import lombok.Data;

/**
 * @ClassName : MSetParam
 * @Author : yq
 * @Date: 2021-04-27
 * @Description :
 */
@Data
public class MSetParam {

    private CacheKeyTO cacheKey;

    private CacheWrapper<Object> result;

    public MSetParam(CacheKeyTO cacheKey, CacheWrapper<Object> result) {
        this.cacheKey = cacheKey;
        this.result = result;
    }
}
