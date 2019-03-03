package test1;

import javax.management.relation.RoleList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * Redis与Spring连接的小程序
 * @author qiang
 *
 */
public class Main {
	public static void main(String[] args) {
		
		 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test1/applicationContext.xml");
		 
		 System.out.println(applicationContext);
	 
		  
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		
		Role role = new Role();
		role.setId(1L);
		role.setRoleName("role_name_1");
		role.setNote("note_1");
		
		redisTemplate.opsForValue().set("role_1", role);
		Role role1 = (Role) redisTemplate.opsForValue().get("role_1");
		
		System.out.println(role1.getRoleName());
		System.out.println(role1.getNote());
	 
		
		
	}
}
