package test4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * 在java中使用哨兵模式
 * @author qiang
 *
 */
public class Main {
	public static void main(String[] args) {
		//连接池的配置
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		
		jedisPoolConfig.setMaxIdle(5);
		jedisPoolConfig.setMaxTotal(10);
		jedisPoolConfig.setMinIdle(5);
		
		//哨兵信息
		//哨兵为独立运行的线程
		Set<String> sentinels = new HashSet<String>(Arrays.asList(
				"192.168.11.128:26379",
				"192.168.11.129:26379",
				"192.168.11.130:26379"
		));
		
		//创建连接池
		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels,jedisPoolConfig);
		
		//获取客户端
		Jedis jedis = pool.getResource();
		 
		
		//执行两个命令
		jedis.set("mykey", "myvalue");
		String myvalue = jedis.get("mykey");
		//打印信息
		System.out.println(myvalue);
		
	}

}
