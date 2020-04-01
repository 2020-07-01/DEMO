package proxy;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 代理逻辑类：建立代理对象与真实对象之间的关系，并返回代理对象
 * 此类必须实现InvocationHandler接口
 * @author qiang
 *
 */
public class JdkProxyExample implements InvocationHandler {

	//真实对象
	private  Object target = null;
	
	//第一步：建立真实对象与代理对象之间的关系，并返回生成的代理对象
	public Object bind(Object target) {
		this.target = target;
		//生成代理对象
		 
		Object object = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
		
		return object;
	}
	
	
	/*
	 * 当代理对象调用真实对象的方法的时候转换为由此方法进行调用
	 * proxy：指的是JDK动态生成的最终代理对象
	 * method：指的是我们所要调用真实对象的某个方法的Method对象
	 * arg：指的是调用真实对象的某个方法时接受的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("进入代理逻辑方法！");
		System.out.println("在调度真实对象之前的服务！");
		/*
		 * 相当于调用SayHelloWorld方法
		 * 通过反射来调用这是对象的方法
		 */
		Object obj = method.invoke(target, args); 
		System.out.println("在调度真实对象之后的服务！");
		
		return obj;
	}
	
	public static void main(String[] args) {
		JdkProxyExample jdkProxyExample = new JdkProxyExample();
		//绑定代理对象与真实对象之间的关系
		HelloWorld proxy = (HelloWorld)jdkProxyExample.bind(new HelloWorldImpl1());
		
		proxy.sayHelloWorld();
	}
	
}
