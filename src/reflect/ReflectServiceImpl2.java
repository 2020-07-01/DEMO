package reflect;import sun.security.jca.GetInstance;

/**
 * ReflectServiceImpl2是包含参数的类
 * 	通过反射构建对象：对于包含参数的类可以通过下面方法构建对象
 * 	
 * @author qiang
 *
 */
public class ReflectServiceImpl2 {
	
	private String name;
	
	public ReflectServiceImpl2(String name) {
		this.name = name;
	}
	
	public void sayHello() {
		System.out.println("Hello "+name);
	}
	
	//通过反射构建对象
	public static ReflectServiceImpl2 getInstance() {
		ReflectServiceImpl2 object = null;
		String path = "reflect.ReflectServiceImpl2";
		try {
			//String.class表示只有一个参数为String的构造方法
			//直接创建好类的实例
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
