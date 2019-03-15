package collectionTest;

import java.util.LinkedList;
import java.util.List;
/**
 * 测试链表
 * @author qiang
 *
 */
public class TestLinkedList {
	
	public static void main(String[] args) {
		/*
		 * 此时linkedList只能访问子类中的重写方法，也就是父类中有的方法
		 * 这是一个明显的向上转型，也就是自己特有的方法不能访问
		 */
		LinkedList<Integer> linkedList = new LinkedList<>(); 
		//添加6个数字
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		
		System.out.println(linkedList);
		linkedList.addFirst(88);
		System.out.println(linkedList);
		

		
	 
		
		 
	}
}
