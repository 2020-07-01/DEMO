package com.cacheframework.manager.redis;

import com.cacheframework.core.CacheKeyTO;
import redis.clients.jedis.PipelineBase;

import java.util.Set;

/**
 * @ClassName : JedisUtil
 * @Author : yq
 * @Date: 2021-05-16
 * @Description :
 */
public class JedisUtil {

    /**
     * 批量删除
     *
     * @param pipelineBase pipelineBase
     * @param keys         keys
     */
    public static void executeDelete(PipelineBase pipelineBase, Set<CacheKeyTO> keys) {

        String hfied;
        String cachekey;
        byte[] key;
        for (CacheKeyTO cacheKeyTO : keys) {
            cachekey = cacheKeyTO.getKey();
            if (cachekey == null || cachekey.isEmpty()) {
                return;
            }
            hfied = cacheKeyTO.getHfield();

            key = AbstractRedisCacheManager.KEY_SERIALIZER.serialize(cachekey);
            if (hfied == null || hfied.isEmpty()) {
                pipelineBase.del(key);
            } else {
                pipelineBase.hdel(key, AbstractRedisCacheManager.KEY_SERIALIZER.serialize(hfied));
            }
        }
    }


}
