package project0317;

import java.util.Stack;

/**
 * 判断链表是否为回文结构
 * @author qiang
 *
 */
public class Test03 {

	public static class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
	//打印链表
	public static void printLinkedList(Node node) {
		System.out.print("Linked List:");
		while(node != null) {
			System.out.print(node.value+" ");
			node = node.next;
		}
	}
	
	//判断链表是否为回文结构
	public static boolean huiwen(Node node) {
		if(node == null)
		{
			return false;
		}
		//先将链表压栈
		Stack<Integer> stack = new Stack<>();
		Node node2  = node;
		while(node2 != null) {
			stack.push(node2.value);
			node2 = node2.next;
		}
		
		//然后弹栈，判断是否与原链表相同
		while(node != null) {
			if(stack.pop() != node.value)
				return false;
			node = node.next;	
		}
		return true;
	}
	
	public static void main(String[] args) {
		Node node = new Node(1);
		node.next = new Node(2);
		node.next.next = new Node(3);
		node.next.next.next = new Node(2);
		 
		
		printLinkedList(node);
		boolean p = huiwen(node);
		System.out.println(p);
	}
	
}
