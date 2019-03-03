package test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

/**
 * SessionCallBack和RedisCallBack这两个接口可以保证所有的操作都来自于通过一个连接
 * 	可以将多个命令放在同一个连接中去执行
 * @author qiang
 *
 */
public class Main2 {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test1/applicationContext.xml");
		 
		 System.out.println(applicationContext);
	 
		  
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		
		Role role = new Role();
		role.setId(1L);
		role.setRoleName("role_name_1");
		role.setNote("note_1");
		
		//此处使用匿名类的方式
		SessionCallback callback = new SessionCallback<Role>() {

			@Override
			public Role execute(RedisOperations operations) throws DataAccessException {
				 
				operations.boundValueOps("role_1").set(role);
				//返回的是一个Role对象
				 return  (Role)operations.boundValueOps("role_1").get();
			}
		};
		
		Role  saveRole = (Role) redisTemplate.execute(callback);
		System.out.println(saveRole.getId());
		
	 
	}

}
