package project0317;

import project0317.Test05.Node;

/**
 * 将单链表中指定值的节点全部删除
 * @author qiang
 *
 */
public class Test06 {
	
	public static class Node {
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
	//删除节点中的指定值
	public static void removeNum(Node node,int num) {
		if(node == null)
		{
			return ;
		}
		
		Node head = node;
		Node newNode = null;
		while(node != null) {
			
			//先找到不用删除的头节点
			if(head.value != num)
			{
				newNode = head;//找到新节点
			}
			break;//退出循环
		}
		
		
		Node cur = newNode.next;
		Node pre = newNode;
		while(cur != null) {
			
			if(cur.value == num)
			{
				pre.next = cur.next;
			}
			
			cur = cur.next;
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
	
	public static void main(String[] args) {
		Node node1 = new Node(7);
		node1.next = new Node(4);
		node1.next.next = new Node(3);
		node1.next.next.next = new Node(4);
		node1.next.next.next.next = new Node(5);
		node1.next.next.next.next.next = new Node(6);
		node1.next.next.next.next.next.next = new Node(3);
		
		printLinkedList(node1);
		removeNum(node1,4);
		printLinkedList(node1);
		 
	}
}
