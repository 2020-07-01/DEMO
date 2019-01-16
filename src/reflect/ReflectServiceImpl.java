package reflect;
/**
 * 	ReflectServiceImpl是一个无参数的类对象
 * 	通过反射构建对象：对于无参数的类可以通过下面方法构建对象
 * @author qiang
 *
 */
public class ReflectServiceImpl {
	
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
	
	
	public static void main(String[] args) {
		
		System.out.println(getInstance());
		getInstance().sayHello("world");
	}
}
