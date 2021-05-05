package com.cacheframework.autoload;

import com.cacheframework.core.CacheWrapper;
import lombok.Data;

/**
 * @ClassName : ProcessingTO
 * @Author : yq
 * @Date: 2021-05-04
 * @Description :
 */
@Data
public class ProcessingTO {

    private volatile long startTime;

    private volatile CacheWrapper<Object> cache;

    private volatile boolean firstFinished = false;

    private volatile Throwable error;

    public ProcessingTO() {
        startTime = System.currentTimeMillis();
    }
}
