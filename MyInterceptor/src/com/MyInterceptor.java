package com;

import java.lang.reflect.Method;
/*
 * 拦截器类
 */
public class MyInterceptor implements Interceptor {

	//在真实对象之前调用进行判断
	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("反射方法前调用!");
		return false;//不反射被代理对象的原有方法
	}
	
	
	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("取代了被代理对象的方法!");
		
	}
	
	//在反射真实对象方法或者aroud方法之后进行调用
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("反射方法后逻辑!");
			
	}

}
