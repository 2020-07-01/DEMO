package collectionTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TestQueue {
	public static void main(String[] args) {
		//实现队列
		Queue queue = new LinkedList<>();
		queue.offer(1);//插入元素1到队列中
		queue.offer(2);
		queue.offer(3);
		
		//获取不移除头元素
		System.out.println(queue.peek());
		
		Queue queue1 = new LinkedList<>();
		System.out.println(queue1.peek());//获取不移除头元素，如果为空返回null
		
		
		
		System.out.println("测试Deque：");
		Deque deque = new LinkedList<>();
		deque.addFirst(1);//添加第一个元素
		deque.addFirst(2);//
		deque.addLast(3);//添加最后一个元素
		
		System.out.println(deque);
		
	}
}
