package test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
/**
 *	字符串的操作小程序
 * @author qiang
 *
 */
public class Main3 {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test1/applicationContext.xml");
		
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		
		//设值
		redisTemplate.opsForValue().set("key1", "value1");
		redisTemplate.opsForValue().set("key2","value2");
		
		//通过key获取值
		String value1 = (String) redisTemplate.opsForValue().get("key1");
		System.out.println(value1);
		
		//通过key删除值
		redisTemplate.delete("key1");
		
		//求长度
		Long length = redisTemplate.opsForValue().size("key2");
		System.out.println(length);
		
		//设置新值并返回旧值
		String  oldValue2 = (String) redisTemplate.opsForValue().getAndSet("key2", "new_value2");
		System.out.println(oldValue2);
	
		//求子串
		String rangVlaue2 = redisTemplate.opsForValue().get("key2",0,2);
		System.out.println(rangVlaue2);
		
		
		}

}
