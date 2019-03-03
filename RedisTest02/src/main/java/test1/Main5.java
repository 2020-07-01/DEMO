package test1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * 测试有序集合
 * 在测试前需要将键值serializer修改为字符串序列化器StringRedisSerializer
 * @author qiang
 *
 */
public class Main5 {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test1/applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		
		//Spring提供接口TypedTuple操作有序集合
		Set<TypedTuple> set1 = new HashSet<TypedTuple>();
		Set<TypedTuple> set2 = new HashSet<TypedTuple>();
		
		int j=9;
		for(int i=1;i<9;i++)
		{
			j--;
			//计算分数的值
			Double scorel1 = Double.valueOf(i);
			String value1 = "x"+i;
			Double scorel2 = Double.valueOf(i);
			String value2 = j % 2 == 1 ? "y" + j : "x" + j;
			//使用Spring提供的默认TypedTuple——DefaultTypedTuple
			TypedTuple typedTuple1 = new DefaultTypedTuple(value1,scorel1);
			set1.add(typedTuple1);	
			TypedTuple typedTuple2 = new DefaultTypedTuple(value2,scorel2);
			set2.add(typedTuple2);
		}
		
		//将元素插入到有序集合zset1
		redisTemplate.opsForZSet().add("zset1",set1);
		redisTemplate.opsForZSet().add("zset2", set2);
		
		//统计总数
		Long size = null;
		
		size = redisTemplate.opsForZSet().zCard("set1");
		//统计分数score
		size = redisTemplate.opsForZSet().count("zset1", 3,6);
		Set set = null;
		//从下标一开始截取5个元素，但是不返回分数，每一个元素是String
		set = redisTemplate.opsForZSet().range("zset1", 1, 5);
		printSet(set);
		
		
	}
	
	//打印普通集合
	public static void printSet(Set set) {
		if(set == null && set.isEmpty())
		{
			return;
		}
		Iterator iterator  = set.iterator();
		while(iterator.hasNext()) {
			Object val = iterator.next();
			System.out.println(val + "\t");
		}
		System.out.println();
	}
}
