package project0315;
/**
 * 用两个栈实现队列
 * 栈：先进后出	队列：先进先出
 * @author qiang
 *
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyQueue {
	private Stack<Integer> Stackpush;
	private Stack<Integer> Stackpop;
	
	public MyQueue() {
		this.Stackpop = new Stack<>();
		this.Stackpush = new Stack<>();
	}
	
	//添加元素到队列中
	public void add(int newNum) {
		Stackpush.push(newNum);
	}
	
	//获取并且移除队列头的元素
	public int poll() {
		if(Stackpush.empty() && Stackpop.isEmpty())
		{
			System.out.println("null");
		}
		//如果stackpop栈为空，就将stackpull的元素移除进去
		else if(Stackpop.empty())
		{
			while(!Stackpush.empty())
			Stackpop.push(Stackpush.pop());
		}
		//此时stackpop栈为队列的输出顺序
		return Stackpop.pop();
	}
	
	//获取但不移除队列的头元素
	public int peek() {
		if(Stackpush.empty() && Stackpop.isEmpty())
		{
			System.out.println("null");
		}
		//如果stackpop栈为空，就将stackpull的元素移除进去
		else if(Stackpop.empty())
		{
			while(!Stackpush.empty())
			Stackpop.push(Stackpush.pop());
		}
		//此时stackpop栈为队列的输出顺序
		return Stackpop.peek();
	}
	
	
	
	public static void main(String[] args) {
		
		MyQueue myQueue = new MyQueue();
		myQueue.add(1);
		myQueue.add(2);
		myQueue.add(3);
		myQueue.add(4);
		myQueue.add(5);

		System.out.println(myQueue.peek());//获取第一个元素不删除
		 
		myQueue.add(6);
		myQueue.add(7);
		myQueue.add(8);
		System.out.println(myQueue.poll()); 
		System.out.println(myQueue.poll());
		System.out.println(myQueue.poll());
		System.out.println(myQueue.poll());
		System.out.println(myQueue.poll());
		
		//此时stackpop中的元素为空
		System.out.println(myQueue.peek());
		 
		
		
		
		
	}
}
