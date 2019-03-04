package test2;

import java.awt.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

public class Main01 {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test2/applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
	
		//这里采用lambda表达式，有返回值
		SessionCallback sessionCallback =  (SessionCallback) (RedisOperations ops) -> {
			ops.multi();//进入队列
			ops.boundValueOps("key1").set("value1");//为key1设置字符串值
			//注意由于命令是之进入队列，而没有去执行，所以此处采用get命令，而value却返回为空
			
			String value = (String) ops.boundValueOps("key1").get();
			System.out.println("事物执行过程中，命令进入队列，而没有执行，所以value为空："+value);
			//执行事务
			java.util.List list = ops.exec();
			value = (String) ops.boundValueOps("key1").get();
			
			return value;
		};
		//执行redis的命令
		String value = (String)redisTemplate.execute(sessionCallback);
		System.out.println(value);
	}

}
