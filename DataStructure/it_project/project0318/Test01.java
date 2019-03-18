package project0318;

import javax.swing.tree.AbstractLayoutCache.NodeDimensions;

/*
 * 合并两个有序的链表
 */
public class Test01 {
	//静态节点类
	public  static class Node {
		public int value;
		public Node next;
		public Node(int data) {
			this.value =data;
		}
	}
	
	//打印链表
	public static void printList(Node node) {
		if(node == null)
			System.out.println("链表为空");
		else {
			while(node != null)
			{
				System.out.print(node.value+ " ");
				node = node.next;
			}
			System.out.println();
		}
	}
	
	public static Node merge(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return head1 != null ? head1 : head2;
		}
		Node head = head1.value < head2.value ? head1 : head2;
		Node cur1 = head == head1 ? head1 : head2;
		Node cur2 = head == head1 ? head2 : head1;
		
		
		Node pre = null;
		Node next = null;
		while (cur1 != null && cur2 != null) {
			if (cur1.value <= cur2.value) {
				pre = cur1;
				cur1 = cur1.next;
			} else {
				next = cur2.next;
				pre.next = cur2;
				cur2.next = cur1;
				pre = cur2;
				cur2 = next;
			}
		}
		pre.next = cur1 == null ? cur2 : cur1;
		return head;
	}
	
	
	public static void main(String[] args) {
		Node node1 = new Node(1);
		node1.next = new Node(3);
		node1.next.next = new Node(5);
		
		
		
		Node node2 =new Node(1);
		node2.next = new Node(2);
		node2.next.next = new Node(4);
		node2.next.next.next = new Node(9);
	 
		
		
		printList(node1);
		printList(node2);
		
		 
		
		Node node = merge(node1, node2);
		printList(node);
		
	}
	
	
}
