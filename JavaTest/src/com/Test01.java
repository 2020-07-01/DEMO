package com;
/**
 * 	数值类型转换的小程序
 * Java语言在涉及short、byte、和char、类型的运算时，首先会把这些类型的变量强制转换为int型，然后对int类型的数值进行计算，
 * 	得到的结果也是int型的
 * 	但是+=是java语言规定的运算符，Java编译器会对其进行特殊的处理
 * @author qiang
 *
 */
public class Test01 {
	
	public static void main(String[] args) {
		
	short a = 123;
	/*
	 * a+2为int类型
	 * 下面这种算法会抛出异常a = a +2;
	 */
	int b =  a + 2;
	System.out.println(b);
	System.out.println(a+=a);
	 
	int i = 1;
	StringBuffer s1 = new StringBuffer("hello ");
	testPassParameter(s1, i);
	
	System.out.println(s1);
	System.out.println(i);
	
	
	//instanceof
	//判断一个引用类型的变量指向的是否是一个类的实例
	String c = "hello";
	if(c instanceof String)
	{
		System.out.println(true);
	}
	
	
	}
	
	/*
	 * 按值传递和按引用传递的区别
	 * 按值传递不会改变实参的值
	 * 按引用传递会改变实参的值
	 */
	public static void testPassParameter(StringBuffer ss1,int n) {
		ss1.append("world!");//引用
		n = 8;
	}
}
