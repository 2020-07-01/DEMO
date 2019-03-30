package nowcoder;

import java.util.ArrayList;
import java.util.Stack;

 

/**
 * 创建一个链表，按链表值从尾到头返回一个arraylist
 * 
 * @author qiang
 *
 */
public class InputLinkList {

	// 创建静态链表类
	public static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}
	//使用两个数组进行转换
	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

		ArrayList<Integer> old = new ArrayList<Integer>();

		int i = 0;
		while (listNode != null) {
			old.add(listNode.val);
			i++;
			listNode = listNode.next;
		}

		ArrayList<Integer> newlist = new ArrayList<Integer>();
		int length = old.size() - 1;
		for (int j = length; j >= 0; j--)
			newlist.add(old.remove(j));

		return newlist;
	}

	// 使用栈进行转换
	public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
		Stack<Integer> stack = new Stack<Integer>();
		while (listNode != null) {
			stack.push(listNode.val);
			listNode = listNode.next;
		}
		ArrayList<Integer> newlist = new ArrayList<Integer>();
		while (!stack.isEmpty()) {
			newlist.add(stack.pop());
		}
		return newlist;

	}

	public static void main(String[] args) {
		InputLinkList inputLinkList = new InputLinkList();

		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(4);
		listNode.next.next = new ListNode(5);
		listNode.next.next.next = new ListNode(0);

		ArrayList<Integer> arrayList = new ArrayList<Integer>();

		arrayList = inputLinkList.printListFromTailToHead1(listNode);
		System.out.println(arrayList);
		
		
		

	}

}
