package nowcoder;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * @author qiang
 *
 */
public class Queue_Stack {
	
	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    //入队
    public void push(int node) {
    	stack1.push(node);
    }
    
    //出队
    public int pop() throws Exception {
    	 if(stack1.isEmpty() && stack2.isEmpty())
    		 throw new Exception("queue is empty");
    	 
    	 else if(stack2.isEmpty()){//如果stack2为空，则要先要加入元素
    		 while(!stack1.isEmpty()) {
    			 stack2.push(stack1.pop());
    		 }
    	}
    	return stack2.pop();
    }

    public static void main(String[] args) throws Exception {
    	Queue_Stack test = new Queue_Stack();
    	test.push(1);
    	test.push(2);
    	test.push(3);
    	
    	System.out.println(test.pop());
	}
    
}


