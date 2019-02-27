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
			//通过forName()方法获取对象
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
