package com;

import java.lang.reflect.Method;

/**
 * 定义拦截器接口
 * @author qiang
 *
 */
public interface Interceptor {

	/*
	 * proxy：代理对象
	 * target：真实对象
	 * args:运行方法参数
	 */
	public boolean before(Object proxy,Object target, Method method,Object[] args) ;
	
	public void around(Object proxy,Object target, Method method,Object[] args) ;
	
	public void after(Object proxy,Object target, Method method,Object[] args) ;

}
