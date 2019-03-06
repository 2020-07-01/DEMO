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
 * 哈希结构类似于map一样，一个对象里面有很多键值对，特别适合存储对象
 * 可以指定默认的字符串序列化器，也可以使用Hash指定的序列化器：hashKeySerializer和hashValueSerializer
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
		
		//相当于hmset命令，设值多个键值对
		redisTemplate.opsForHash().putAll(key, map);
		//相当于hmset命令，设置单个键值对
		redisTemplate.opsForHash().putIfAbsent(key, "f3", "6");
		//输出value值
		printValueForHash(redisTemplate,key,"f3");
		//判断hash结构中是否存在file字段，相当于hexists key files
		boolean exits = redisTemplate.opsForHash().hasKey(key, "f3");
		System.out.println(exits);
		
		//相当于hgetall命令,获取所有hash结构中的键值对
		Map keyValMap = redisTemplate.opsForHash().entries(key);
		
		
		
	}
	
	private static void printValueForHash(RedisTemplate redisTemplate,String key,String filed) {
		Object value = redisTemplate.opsForHash().get(key, filed);
		System.out.println(value);
	}
}
