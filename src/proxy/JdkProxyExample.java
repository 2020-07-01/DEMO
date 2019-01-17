package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 每次生成代理类对象，都要指定一个实现了InvocationHandler接口的代理逻辑类(JdkProxyExample)
 * @author qiang
 *
 */
public class JdkProxyExample implements InvocationHandler {
	//要代理的真实对象，也就是操作业务的实际对象
	private Object target = null;
	/**
	 * 建立代理对象与真实对象的代理关系，并返回代理对象
	 * Proxy:提供动态方法创建代理类和实例
	 * @param target
	 * @return 代理类的实例
	 */
	public Object bind(Object target) {
		this.target = target;
		//newProxyInstance():返回指定接口的代理类的实例
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);	
	}
	
	/**
	 * 代理方法逻辑
	 * @param proxy 代理类的实例
	 * @param method 当前调度方法
	 * @param args 当前方法参数
	 * @return 代理结果返回
	 * @throws Throwable 异常
	 */
	@Override
	//invoke是代理逻辑方法的现实方法
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("进入代理逻辑方法");
		System.out.println("在调度真实对象方法之前的服务");
		System.out.println("当前调用的方法："+method.getName());
		//当代理对象调用真实对象的方法时，会自动跳转到InvocationHandler的对象的Invoke方法上来调用
		//需要指定被代理的真实对象和传入的参数
		Object object  = method.invoke(target, args);
		System.out.println(method.getName()+"的返回值："+object);
		System.out.println("在调度真实对象方法之后的服务");
		//返回method方法执行后的返回值
		return object;
	}
	
}
