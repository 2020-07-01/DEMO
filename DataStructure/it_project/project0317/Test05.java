package project0317;

import java.util.HashSet;
import java.util.Set;


/**
 * 删除单项链表中的重复节点
 * @author qiang
 *
 */
public class Test05 {
	//创建节点
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
		
	//删除重复节点
	public static void removeNode(Node node) {
		Set<Integer> set = new HashSet<Integer>();
		//先将第一个节点添加到set中
		set.add(node.value);
		
		Node pre = node;
		Node cur = node.next;
		while(cur != null) {
			//如果此节点已经存在
			if(set.contains(cur.value))
			{
				pre.next = cur.next;
			}
			else
			{
				set.add(cur.value);
				pre = cur;//将此节点给先前的节点，等待下一次的判断
			}
			
			cur = cur.next;
		}
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
		removeNode(node1);
		printLinkedList(node1);
		
	}

}
