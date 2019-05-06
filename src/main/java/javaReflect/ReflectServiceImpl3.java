package javaReflect;

import java.lang.reflect.Method;

/**
 *  反射生成对象和反射调度方法
 * @author qiang
 *
 */
public class ReflectServiceImpl3 {
	
	public void sayHello(String name) {
		System.out.println("Hello "+name);
	}
		
	public static Object reflectMethod() {
		Object returnObj = null;
		String path = "com.ReflectServiceImpl3";
		try {
			//反射对象
			returnObj = (ReflectServiceImpl3)Class.forName(path).newInstance();
			/*
			 * getMethod()：返回一个方法对象，第一个参数是参数名称，第二个参数是参数类型，他是一个列表，多个参数可以编写多个参数类型
			 */
			Method method = returnObj.getClass().getMethod("sayHello", String.class);
			/*
			 * 反射方法
			 * 第一个参数为target指明使用那个对象调用方法
			 * 第二个是参数，等同于target.sayHello("张三")
			 */
			returnObj = method.invoke(returnObj, "张三");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return returnObj;
	}
	
	//测试
	public static void main(String[] args) {
		
		reflectMethod();
	}
}
