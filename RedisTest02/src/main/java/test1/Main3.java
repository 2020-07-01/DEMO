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
		
		//设置值
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
		//获取值 
		String value2 = (String) redisTemplate.opsForValue().get("key2");
		System.out.println(value2);
		
		//求子串
		//此处代码有bug返回的子串乱码
		String rangValue2 = redisTemplate.opsForValue().get("key2",0,3);
		System.out.println(rangValue2);
		
		//追加字符到末尾,返回新串的长度
		int newLen = redisTemplate.opsForValue().append("key2","_app");
		System.out.println(newLen);
		
		String appendValue2 = (String) redisTemplate.opsForValue().get("key2");
		System.out.println(appendValue2);
		
		/*
		 * 测试redis的运算
		 * 这里必须使用字符串序列化器，这样redis保存的是字符串
		 * 如果采用其他序列化器，则保存的不是字符串会产生异常
		 */
		//设置i值为9
		redisTemplate.opsForValue().set("i","9");
		printCurrentValue(redisTemplate, "i");

		redisTemplate.opsForValue().increment("i",1);//i+1
		printCurrentValue(redisTemplate, "i");
		
		/*
		 * redisTemplate并没有支持减法
		 * 通过获得工厂连接获得底层的redis连接对象
		 * 对键进行序列化,转换为二进制进行操作
		 */
		redisTemplate.getConnectionFactory().getConnection().decr(redisTemplate.getKeySerializer().serialize("i"));
		printCurrentValue(redisTemplate, "i");
		
		//在原有字段上减去整数
		redisTemplate.getConnectionFactory().getConnection().decrBy(redisTemplate.getKeySerializer().serialize("i"),6);
		printCurrentValue(redisTemplate, "i");
		
		
		redisTemplate.opsForValue().increment("i",2.3);//i+1
		printCurrentValue(redisTemplate, "i");
		
		
		}
	//打印当前key的值
	public static void printCurrentValue(RedisTemplate redisTemplate,String key) {
		String i = (String) redisTemplate.opsForValue().get(key);
		
		System.out.println(i);
	}

}
