package proxy;

/**
 * 测试JDK动态代理
 * @author qiang
 *
 */
public class Main {
	public static void main(String[] args) {
		//创建真实的对象
		HelloWorldImpl target = new HelloWorldImpl();
		//通过JdkProxyexample的构造器生成一个InvocationHandler的实例对象
		JdkProxyExample handler = new JdkProxyExample();
		//代理对象proxy与被代理对象target直间绑定关系
		HelloWorld proxy = (HelloWorld)handler.bind(target);
		
		proxy.sayHelloWorld();
		//invoke方法是如何被调用的
	}
}
