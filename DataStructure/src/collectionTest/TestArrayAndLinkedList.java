package collectionTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestArrayAndLinkedList {
	public static void main(String[] args) {
		
		
		List<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(1);
		arrayList.add(4);
		arrayList.add(0,10);//在指定位置添加元素
		arrayList.add(2,30);
		System.out.println("输出arrayList"+arrayList);
		
		//从已经给出的合集中创建一个线性表
		LinkedList<Object> linkedList = new LinkedList<>(arrayList);
		linkedList.add(1,"red");
		
		System.out.println(linkedList);
		
		System.out.println("从前往后遍历输出：");
		ListIterator<Object> iterator = linkedList.listIterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next());
		}
		System.out.println();
		/*
		 * 只有ListIterator迭代器可以实现从后往前进行遍历
		 * 
		 */
		System.out.println("从后往前遍历输出：");
		iterator = linkedList.listIterator();
		while(iterator.hasPrevious()) {
			System.out.println(iterator.previous());
		}
		
		System.out.println(linkedList.get(2));
		
		 
	}

}
