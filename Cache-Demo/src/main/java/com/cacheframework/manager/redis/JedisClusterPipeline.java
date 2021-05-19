package com.cacheframework.manager.redis;

import redis.clients.jedis.Client;
import redis.clients.jedis.PipelineBase;

import java.util.List;

/**
 * @ClassName : JedisClusterPipeline
 * @Author : yq
 * @Date: 2021-05-16
 * @Description : redis 批量操作
 */
public class JedisClusterPipeline extends PipelineBase {



    protected void sync(){

    }

    private void innerSync(List<Object> formatted){



    }


    @Override
    protected Client getClient(String key) {

        

        return null;
    }

    @Override
    protected Client getClient(byte[] key) {
        return null;
    }
}
