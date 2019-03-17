package project0317;

import project0317.Test06.Node;

/**
 * 在不给定链表头节点的情况下，删除给定的一个节点
 * 方法：将后面节点的值赋值给此节点，然后跳过后面的节点，相当于删除给定的节点
 * @author qiang
 *
 */
public class Test07 {

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
	
	//删除指定节点
	public static void rmemoveNode(Node node) {
		if(node == null)
		{
			return;
		}
		
		Node cur = node.next;
		if(cur == null)
			throw new RuntimeException("can not remove last node.");
		
		node.value = cur.value;
		node.next = cur.next;
		 
	}
	
	public static void main(String[] args) {
		Node node1 = new Node(7);
		node1.next = new Node(4);
		node1.next.next = new Node(3);
		node1.next.next.next = new Node(4);
		node1.next.next.next.next = new Node(5);
		node1.next.next.next.next.next = new Node(6);
		node1.next.next.next.next.next.next = new Node(1);
		
		printLinkedList(node1);
		Node node = node1.next.next;
		rmemoveNode(node);
		printLinkedList(node1);
		 
		
		
	}
}
