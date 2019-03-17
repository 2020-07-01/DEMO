package list;

import java.util.Currency;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.w3c.dom.ls.LSInput;

/**
 * 实现无序单项链表
 * @author qiang
 *
 */
public class MyLinkedList {

	private Node head;//头节点
	private Node tail;//尾节点
	private int size = 0 ;//记录节点数
	
	//静态节点类-内部类
	public static class Node {
		public int value;
		public Node next;
		
		//创建带有元素的节点
		public Node(int data) {
			this.value = data;
		}
		//创建空节点
		public Node() {
		}
		 
		
	}
	
	//将指定的元素添加到表头
	public void addFirst(int num) {
		Node newNode = new Node(num);
		if(size == 0){
            head = newNode;
            tail = newNode;
         }
		else
		{
            newNode.next = head;
            head = newNode;//重置头节点
         }
		size++;
	}
	
	//将指定的元素添加到表尾
	public  void addEnd(int num) {
		Node newNode = new Node(num);
		if(size == 0)
		{
			head = newNode;//指定头节点
			tail = newNode;//指定尾节点
		}
		else 
		{
			tail.next = newNode;
			tail = newNode;//重置尾节点
 		}
		size ++;
	}
	
	//返回列表中元素的数量
	public int size() {
		return size;
	}
	
	//向指定位置的节点添加元素
	public void add(int index,int num) {
		
	}
	
	//返回链表中指定位置的元素
	public int get(int num) {
		
		return num;
	}
	
	//删除尾节点
	public int removeEnd() {
		if(size == 0) return 0;
		
		else if(size == 1) {
			Node temp = head;
			head = tail = null;
			size = 0;
			return head.value;
		}
		
		else {
			Node cur = head;
			for(int i=0;i<size-2;i++)
			{
				cur = cur.next;
			}
			Node temp = tail;
			tail = cur;
			tail.next = null;
			size--;
			return temp.value;
		}
	}
	//删除头节点
	public int removeFirst() {
		if(size == 0) return 0;
		else if(size == 1) {
			Node temp = head;
			head = tail = null;
			size = 0;
			return head.value;
		}
		else {
			Node temp = head;
			head = head.next;
			size--;
			return temp.value;
		}
	}
	
	//返回并删除指定位置处的元素
	public int remove(int index) {
		if(index < 0  || index >= size)
			return 0;
		else if(index == 0)
		{
			 return removeFirst();
		}
		else if(index == size)
		{
			return removeEnd();
		}
		else {
			Node pre = head;
			for(int i = 1;i<index;i++)
			{
				pre = pre.next;
			}
			Node cur = pre.next;
			pre.next = cur.next;
			size--;
			return cur.value;
		}
	}
}
