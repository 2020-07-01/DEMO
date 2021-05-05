package com.cacheframework.autoload;

import com.cacheframework.core.CacheKeyTO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName : AutoloadCacheController
 * @Author : yq
 * @Date: 2021-04-27
 * @Description :
 */

@Slf4j
public class AutoloadCacheController {


    private final CacheHandler autoloadCacheHandler;

    public AutoloadCacheController(CacheHandler autoloadCacheHandler) {
        this.autoloadCacheHandler = autoloadCacheHandler;
    }

    public boolean removeCache(String key, String hfiled) {

        CacheKeyTO cacheKeyTO = new CacheKeyTO(autoloadCacheHandler.getAutoLoadConfig().getNamespace(), key, hfiled);

        try {

            Set<CacheKeyTO> keys = new HashSet<>();
            keys.add(cacheKeyTO);

        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return false;
        }
        return false;
    }
}
