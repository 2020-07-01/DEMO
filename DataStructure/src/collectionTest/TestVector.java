package collectionTest;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class TestVector {
	public static void main(String[] args) {
		
		Collection vector = new Vector<>();
		vector.add(1);
		
		
		//在其基础之上增加了出栈和入栈的操作
		Stack stack = new Stack<>();
		
		stack.add(1);
		stack.add(2);
		stack.add(3);
		stack.add(4);
		System.out.println("输出栈："+stack);
		
		stack.push(5);//新增元素到栈的顶部
		System.out.println("输出栈："+stack);
		System.out.println("返回栈顶部的元素:"+stack.peek());
		
		
		
		
 
		
		
		 
		
		 
		
		
	}

}
