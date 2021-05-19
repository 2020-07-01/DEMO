package com.cacheframework.manager.redis;

import com.cacheframework.common.MSetParam;
import com.cacheframework.core.CacheKeyTO;
import com.cacheframework.core.CacheWrapper;
import com.cacheframework.manager.ICacheManager;
import com.cacheframework.serializer.ISerializer;
import com.cacheframework.serializer.StringSerializer;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName : AbstractRedisCacheManager
 * @Author : yq
 * @Date: 2021-04-27
 * @Description :
 */
@Slf4j
public abstract class AbstractRedisCacheManager implements ICacheManager {

    public static final StringSerializer KEY_SERIALIZER = new StringSerializer();

    protected final ISerializer<Object> serializer;

    /**
     * Hash的缓存时长
     * 等于0时，永久缓存
     * 大于0时，防止不用缓存占用内存
     * 小于0时，使用@Cache中的expire
     */
    private int hashExpire = -1;

    public AbstractRedisCacheManager(ISerializer<Object> serializer) {
        this.serializer = serializer;
    }

    protected abstract IRedis getRedis();

    /**
     * 往缓存中写数据
     *
     * @param cacheKeyTO 缓存key
     * @param result     缓存值
     * @param method     method
     */
    @Override
    public void setCache(CacheKeyTO cacheKeyTO, CacheWrapper<Object> result, Method method) {

        if (null == cacheKeyTO) {
            return;
        }

        String cacheKey = cacheKeyTO.getKey();
        if (cacheKey == null || cacheKey.isEmpty()) {
            return;
        }

        try (IRedis redis = getRedis()) {
            byte[] key = KEY_SERIALIZER.serialize(cacheKey);
            byte[] value = serializer.serialize(result);
            String hfield = cacheKeyTO.getHfield();
            if (null == hfield || hfield.isEmpty()) {
                int expire = result.getExpire();
                if (expire == NEVER_EXPIRE) {
                    redis.set(key, value);
                } else {
                    redis.setex(key, expire, value);
                }
            } else {
                byte[] field = KEY_SERIALIZER.serialize(hfield);
                int hExpire = hashExpire < 0 ? result.getExpire() : hashExpire;
                if (hExpire == NEVER_EXPIRE) {
                    redis.hset(key, field, value);
                } else {
                    redis.hset(key, field, value, hExpire);
                }
            }
        } catch (Exception e) {
            log.error("");
        }
    }

    /**
     * 往缓存写数据
     *
     * @param method method
     * @param params params
     */
    @Override
    public void mset(Method method, Collection<MSetParam> params) {

        if (null == params || params.isEmpty()) {
            return;
        }
        try (IRedis redis = getRedis()) {
            redis.mset(params);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 根据缓存key获取缓存中的数据
     *
     * @param cacheKeyTo cacheKeyTo
     * @param method     method
     * @return CacheWrapper
     */
    @Override
    public CacheWrapper<Object> get(CacheKeyTO cacheKeyTo, Method method) {
        if (null == cacheKeyTo) {
            return null;
        }
        String cacheKey = cacheKeyTo.getKey();
        if (null == cacheKey || cacheKey.isEmpty()) {
            return null;
        }
        CacheWrapper<Object> res = null;
        String hfield;
        Type returnType = null;
        try (IRedis redis = getRedis()) {
            byte[] val;

            hfield = cacheKeyTo.getHfield();
            if (null == hfield || hfield.isEmpty()) {
                val = redis.get(KEY_SERIALIZER.serialize(cacheKey));
            } else {
                val = redis.hget(KEY_SERIALIZER.serialize(cacheKey), KEY_SERIALIZER.serialize(hfield));
            }
            if (null != method) {
                returnType = method.getGenericReturnType();
            }
            res = (CacheWrapper<Object>) serializer.deserialize(val, returnType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return res;
    }

    /**
     * @param method
     * @param returnType
     * @param keys
     * @return
     */
    @Override
    public Map<CacheKeyTO, CacheWrapper<Object>> mget(Method method, Type returnType, Set<CacheKeyTO> keys) {
        return null;
    }

    /**
     * @param keys
     */
    @Override
    public void delete(Set<CacheKeyTO> keys) {

        if (null == keys || keys.isEmpty()) {
            return;
        }
        try (IRedis redis = getRedis()) {
            redis.delete(keys);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public int getHashExpire() {
        return hashExpire;
    }

    public void setHashExpire(int hashExpire) {
        if (hashExpire < 0) {
            return;
        }
        this.hashExpire = hashExpire;
    }
}



