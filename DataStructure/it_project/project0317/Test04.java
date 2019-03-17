package project0317;

import java.util.Stack;


/**
 * 利用链表将两个整数链表相加
 * @author qiang
 *
 */
public class Test04 {

	public static class Node {
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
	//打印链表
	public static void printLinkedList(Node head) {
		System.out.print("print list:");
		while(head != null) {
			System.out.print(head.value+" ");
			head = head.next;
		}
		System.out.println();
	}
	
	//利用栈结构进行求解
	//压栈后生成链表的逆序，从地位到高位输出
	public  static Node addList1(Node node1,Node node2) {
		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();
		
		while(node1 != null) {
			s1.push(node1.value);
			node1 = node1.next;
		}
		while(node2 != null) {
			s2.push(node2.value);
			node2 = node2.next;
		}
		
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		int ca = 0;
		Node pre = null;
		Node node = null;//存储新生成的链表
		while (!s1.isEmpty() || !s2.isEmpty()) {
			n1 = s1.isEmpty() ? 0 : s1.pop();
			n2 = s2.isEmpty() ? 0 : s2.pop();
			n = n1 + n2 + ca;
			pre = node;
			node = new Node(n % 10);
			node.next = pre;
			ca = n / 10;
		}
		
		if (ca == 1) {
			pre = node;
			node = new Node(1);
			node.next = pre;
		}
		 
		return node;
		 
		
	}
	
	public static void main(String[] args) {
		Node node1 = new Node(7);
		node1.next = new Node(6);
		node1.next.next = new Node(3);
		node1.next.next.next = new Node(4);
		node1.next.next.next.next = new Node(5);
		
		Node node2 = new Node(6);
		node2.next = new Node(9);
		node2.next.next = new Node(3);
		node2.next.next.next = new Node(4);
		node2.next.next.next.next = new Node(5);
		
		printLinkedList(node1);
		printLinkedList(node2);
		
		Node node3 = addList1(node1,node2);
		printLinkedList(node3);
		
	}
}
