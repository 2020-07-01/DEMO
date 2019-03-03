package test1;
 

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.annotation.ApplicationScope;

import com.mysql.jdbc.SingleByteCharsetConverter;

/**
 * 哈希结构的操作小程序
 * @author qiang
 *
 */
public class Main4 {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test1/applicationContext.xml");
	
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		
		String key = "hash";
		Map<String, String> map = new HashMap<String ,String>();

		map.put("f1", "value1");
		map.put("f2", "value2");
		
		//相当于hmset命令
		redisTemplate.opsForHash().putAll(key, map);
		//相当于hmset命令
		redisTemplate.opsForHash().putIfAbsent(key, "f3", "6");
		//输出value值
		printValueForHash(redisTemplate,key,"f3");
		//判断hash结构中是否存在file字段
		
		
	}
	
	private static void printValueForHash(RedisTemplate redisTemplate,String key,String filed) {
		Object value = redisTemplate.opsForHash().get(key, filed);
		System.out.println(value);
	}
}
