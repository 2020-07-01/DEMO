import org.apache.commons.pool2.PooledObject;
import redis.clients.jedis.JedisShardInfo;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author :qiang
 * @date :2019/10/27 下午3:34
 * @description :
 * @other :
 */
public class Main {


    private static String[] servers = {"192.168.0.0.111", "192.168.0.1.111", "192.168.0.2.111", "192.168.0.3.111", "192.168.0.4.111"};

    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    static {
        for (int i = 0; i < servers.length; i++) {
            int hash = getHash(servers[i]);
            System.out.println("[" + servers[i] + "]加入到集合中，其Hash值为" + hash);
            sortedMap.put(hash, servers[i]);
        }
    }

    /**
     * 计算服务器Hash值
     *
     * @param server
     * @return
     */
    private static int getHash(String server) {

        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < server.length(); i++) {
            hash = (hash ^ server.charAt(i)) * p;
            hash += hash << 13;
            hash ^= hash >> 7;
            hash += hash << 3;
            hash ^= hash >> 17;
            hash += hash << 5;
        }
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }


    private static String getServer(String key) {

        //计算hash
        int hash = getHash(key);
        //得到大于该Hash值的所有Map

        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap.isEmpty()) {
            //如果为空，则返回第一个节点,此时形成一个闭环
            int i = sortedMap.firstKey();
            return sortedMap.get(i);
        } else {
            //如果不为空，则返回最近的节点
            int i = subMap.firstKey();
            return subMap.get(i);
        }
    }

    public static void main(String[] args) {

/*

        String[] keys = {"太阳", "月亮", "星星","1312"};

        for (int i = 0; i < keys.length; i++) {
            System.out.println("字符串" + keys[i] + "服务器为：" + getServer(keys[i]));
        }
*/

        Random random = new Random();
        random.nextInt(10);



        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        concurrentSkipListMap.put("5","5");
        concurrentSkipListMap.put("6","6");
        concurrentSkipListMap.put("100","100");
        concurrentSkipListMap.put("50","50");

        System.out.println(concurrentSkipListMap.get("5"));


        System.out.println("2147483649");
        System.out.println(Integer.MAX_VALUE);

    }

}

