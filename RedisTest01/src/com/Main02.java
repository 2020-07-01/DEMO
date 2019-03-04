package com;

 
import java.util.List;
import java.util.logging.LoggingMXBean;

import org.springframework.data.redis.connection.Pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * 测试redis流水线操作的程序
 * @author qiang
 *
 */
public class Main02 {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localHost",6379);//连接redis
		long start = System.currentTimeMillis();
		//开启流水线
		Pipeline pipeline = jedis.pipelined();
		for(int i= 0;i < 100000;i++) {
			int j = i+1;
			pipeline.set("piepeline_key_" +j,"piepeline_value_"+j);
			pipeline.get("piepeline_key_" +j);
			
			
		}
		
		List result = pipeline.syncAndReturnAll();
		Long end = System.currentTimeMillis();
		
		System.out.println("耗时："+(end - start)+"毫秒");
	}
}
