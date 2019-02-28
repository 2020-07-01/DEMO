package com;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理逻辑类
 * @author qiang
 *
 */
public class InterceptorJdkProxy implements InvocationHandler {
	//真实对象
	private Object target;
	//拦截器全限定名称
	private String interceptorClass = null;
	
	public InterceptorJdkProxy(Object target,String  interceptorClass) {
		this.interceptorClass = interceptorClass;
		this.target = target;
	}
	
	
	//建立代理对象与真实对象之间的关系,并放回代理对象
	public static  Object bind(Object target,String interceptorClass) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), 
				new InterceptorJdkProxy(target, interceptorClass));
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(interceptorClass == null) {
			//如果没有设置拦截器则直接反射原有的方法
			return method.invoke(target, args);
		}

		Object result = null;
		//通过反射生成拦截器
		Interceptor interceptor = (Interceptor)Class.forName(interceptorClass).newInstance();
		//调用拦截器的前置方法
		if(interceptor.before(proxy, args, method, args)) {
			//如果为true,反射target的方法
			result = method.invoke(target, args);
		}
		//否则执行around方法
		else {
			interceptor.around(proxy, args, method, args);
		}
		
		//最后调用后置方法
		interceptor.after(proxy, args, method, args);
		return null;
	}

		public static void main(String[] args) {
			//创建代理类
			HelloWorld helloWorld = (HelloWorld)InterceptorJdkProxy.bind(new HelloWorldImpl1(),"com.MyInterceptor");
			helloWorld.sayHelloWorld();
		}
	 
	
 

}
