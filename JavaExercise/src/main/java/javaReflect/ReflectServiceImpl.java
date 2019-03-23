package com;
/**
 * 	ReflectServiceImpl是一个无参数的类对象
 * 	通过反射配置信息构建对象：无参数类的反射生成实例
 * @author qiang
 *
 */
public class ReflectServiceImpl {
	
	public void sayHello(String name) {
		System.out.println("Hello "+name);
	}
	
	//通过反射的方法生产一个对象将其返回
	public static ReflectServiceImpl getInstance() {
		ReflectServiceImpl object = null;
		String path = "com.ReflectServiceImpl";
		try {
			/*forName(String className):返回与具有给定字符串名称的类或接口关联的类对象。
			 * newInstance()：创建由这个类对象表示的类的新实例
			 */
			object = (ReflectServiceImpl) Class.forName(path).newInstance();
			
		} catch (Exception e) {
		}
		
		return object;
	}
	
	//测试
	public static void main(String[] args) {
		//生成一个对象并将其输出
		System.out.println(getInstance());
		//调用对象的方法
		getInstance().sayHello("world");
	}
}
