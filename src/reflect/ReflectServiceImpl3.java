package reflect;

import java.lang.reflect.Method;

/**
 * 	此类演示如何反射方法：在使用反射方法之前要获取方法对象，得到了方法才能够去反射
 * @author qiang
 *
 */
public class ReflectServiceImpl3 {
	
	public void sayHello(String name) {
		System.out.println("Hello "+name);
	}
	
	//通过反射返回对象
	public static ReflectServiceImpl getInstance() {
		ReflectServiceImpl object = null;
		String path = "reflect.ReflectServiceImpl";
		try {
			//通过forName()方法获取对象
			object = (ReflectServiceImpl) Class.forName(path).newInstance();
			
		} catch (Exception e) {
		}
		return object;
	}
	
	public static Object reflectMethod() {
		Object returnObj = null;
		ReflectServiceImpl3 target = new ReflectServiceImpl3();
		try {
			
			Method method = ReflectServiceImpl3.class.getMethod("sayHello", String.class);
			//反射方法
			returnObj = method.invoke(target, "张三");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return returnObj;
	}
	
	
	public static void main(String[] args) {
		
		reflectMethod();
	}
}
