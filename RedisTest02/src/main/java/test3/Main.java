package test3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class Main {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test3/applicationContext.xml");
		
		RedisTemplate redisTemplate =  applicationContext.getBean(RedisTemplate.class);
		
		String channel = "chat";
		//此方法向渠道发送消息
		redisTemplate.convertAndSend(channel, "I am a lazy!");
	}
}
