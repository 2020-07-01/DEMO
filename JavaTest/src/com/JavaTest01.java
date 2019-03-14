package com;

/**
 * 字符串创建与存储机制探索
 * @author qiang
 *
 */
public class JavaTest01 {
	public static void main(String[] args) {
		
		int a = 1;
		int b = 2;
		System.out.println(a==b); //false
		
		
		String s1 = new String("Hello");
		String s2 = new String("Hello");
		
		System.out.println(s1 == s2); //false
		System.out.println(s1.equals(s2)); //true
		
		
		String string1 = "abc"; //创建好放到常量池中
		String string2 = "ab"+"c"; //将"ab"+"c"转换为字符串常量“abc”放到常量池中
		
		String string3 = new String("abc"); //在运行时把“abc”放到堆中
		String string4 = "a";
		
		System.out.println(string1 == string2);//存储的是相同的引用
		System.out.println(string1==string4);//存储的是不同的引用
		System.out.println(string1 == string3);//string1的引用在常量池中，s3的引用在堆中
		
		System.out.println(string1.equals(string2));//比较的是存储的内容true
		System.out.println(string1.equals(string3));//比较的是存储的内容，true
		
	}

}
