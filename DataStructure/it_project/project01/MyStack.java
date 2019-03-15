package project01;

import java.util.Stack;

/**
 * 实现getMin()操作
 * 思路:在自己的栈中定义两个栈，stackData实现数据的存入操作，stackMin实现存储最小值的操作，从栈顶到栈低，从最小值到最大值
 * 注意：如果值小于stackMin中的栈顶值，则不进行压入
 * @author qiang
 *
 */


public class MyStack {
	
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	public MyStack() {
		this.stackData = new Stack<Integer>();
		this.stackMin = new Stack<Integer>();
	}
	
	//添加元素到栈顶
	public void push(Integer newNum) {
		 //如果stackMin为空，则将newNum加入
		if(stackMin.isEmpty())
		 {
			 this.stackMin.push(newNum);
		 }
		 //如果newMin小于getMin()，则在stackMin中加入新的最小值
		 else if(newNum < this.getMin())
		 {
			 this.stackMin.push(newNum);
		 }
		 //将数据存入到stackData中
		 this.stackData.push(newNum);
	 }
	
	 //删除栈顶的元素，并将其返回
	 public int pop() {
		 
		 if(this.stackMin.isEmpty())
			 System.out.println("stackMin为1空");
		 //value为stackData中栈顶的元素
		 int value = this.stackData.pop();
		 //如果value==getMin,就将stackMin中的栈顶元素删除
		 if(value == this.getMin())
		 {
			 this.stackMin.pop();//返回栈顶元素并删除
		 }
		 
		 return value;
		 
	 }
	 //返回栈中的最小值
	 public Integer getMin() {
		 if(this.stackMin.isEmpty())
		 {
			 System.out.println("stackMin为空");
		 }
		 return stackMin.peek();//返回栈顶元素并不删除
	 }
	 
	 //测试
	 public static void main(String[] args) {
		MyStack myStack = new MyStack();
		myStack.push(5);
		myStack.push(3);
		myStack.push(1);
		myStack.push(4);
		
		System.out.println("栈顶元素:"+myStack.pop());
		System.out.println("最小元素:"+myStack.getMin());
	}
	
}
