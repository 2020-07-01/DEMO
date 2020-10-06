package javaReflect; 

/**
 * ReflectServiceImpl2是包含参数的类
 * 通过反射构建对象：有参数类的反射生成实例
 * @author qiang
 *
 */
public class ReflectServiceImpl2 {
	
	private String name;
	
	//构造函数
	public ReflectServiceImpl2(String name) {
		this.name = name;
	}
	
	public void sayHello() {
		System.out.println("Hello "+name);
	}
	
	//通过反射构建对象
	public static ReflectServiceImpl2 getInstance() {
		ReflectServiceImpl2 object = null;
		String path = "com.ReflectServiceImpl2";
		try {
			/*
			 * getConstructor()方法的参数可以是多个，这里定义为String.class,意味有且只有一个参数类型为String的构建方法
			 *   通过整个方法也可以对重名方法进行排除
			 */
			object = (ReflectServiceImpl2) Class.forName(path).getConstructor(String.class).newInstance("张三");
		} catch (Exception e) {
			
		}
		return object;
	}
	
	public static void main(String[] args) {
		System.out.println(getInstance());
		getInstance().sayHello();
		
	}
}
