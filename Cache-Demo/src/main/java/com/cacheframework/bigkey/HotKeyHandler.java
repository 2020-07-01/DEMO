package com.cacheframework.bigkey;

import redis.clients.jedis.Jedis;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName : HotKeyHandler
 * @Author : yq
 * @Date: 2021-05-24
 * @Description :
 */
public class HotKeyHandler {

    /**
     * 热点key 读多写少场景
     * 某个热销商品或者热点新闻 短时间内对热点key进行大量请求
     * 服务端会对数据进行切片处理，此时会对某个主机上的热点key进行访问
     * 当访问超过主机的极限时，会有问题产生
     * 解决方案：
     * 服务端做缓存
     * 备份热点key：即热点key+随机数，随机分配到Redis的其他节点之中
     */


    /**
     * 如何找到热点key?
     * DB中的数据做更新删除之后，如何保证数据的一致性？
     */


    private ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    Jedis jedis;


    //集群数量
    int n = 3;

    public void handler(String hotkey, int expire) {
        int random = getRandom();
        String bakHotKey = hotkey + "_" + random;

        String data = jedis.get(bakHotKey);

        if (data == null) {
            data = jedis.get(hotkey);
            if (data == null) {
                //分布式锁防止大量请求打到DB
                //后来者请求可做排队处理
                //CAS 重试 + 重试次数上限
                data = getDB(hotkey);
                //写入主数据
                jedis.setex(hotkey, expire, data);
            }
            //写入备份数据
            jedis.setex(bakHotKey, expire + getRandom(), data);
        }
    }

    public String getDB(String key) {

        return "";
    }

    public int getRandom() {
        //防止相同后缀key做映射之后还是集中到同一个节点上
        int m = n * 2;
        return threadLocalRandom.nextInt(m);
    }

}
