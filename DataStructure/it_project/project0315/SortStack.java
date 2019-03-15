package project0315;

import java.util.Stack;


/**
 * 用两个栈实现栈的排序
 * @author qiang
 *
 */
public class SortStack   {
	
	Stack<Integer> stack;
	Stack<Integer> help ;
	
	public SortStack() {
	
		this.stack = new Stack<>();
		this.help = new Stack<>();
		
	}
	public void sortTest() {
		while(!stack.isEmpty()) {
		
			int cur = stack.pop();
			while(!help.empty() && help.peek() > cur) {
				//关键步骤
				stack.push(help.pop());
			}
			 help.push(cur);
		}
		
		while(!help.empty()) {
			stack.push(help.pop());
		}
		
	}
 
	
	public static void main(String[] args) {
		 
		SortStack sortStack = new SortStack();
		sortStack.stack.add(5);
		sortStack.stack.add(3);
		sortStack.stack.add(2);
		sortStack.stack.add(4);
		sortStack.stack.add(1);
		
		sortStack.sortTest();
		
		System.out.println("元素数量："+sortStack.stack.size());
		for(int i = 0;i <= 5;i++)
		{
			System.out.println(sortStack.stack.pop());
		}
		
	}

}
