package collectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.lang.model.element.Element;

public class TestCollection {
	public static void main(String[] args) {
		
		ArrayList<String> collection1 = new ArrayList<>();
 
		collection1.add("a");
		collection1.add("b");
		collection1.add("c");
		collection1.add("d");
		collection1.add("b");
		System.out.println(collection1);
		//进行排序
		Collections.sort(collection1);
		 
		System.out.println(collection1);
		System.out.println(collection1.size());
		
		//创建数组先线性表的副本，保持原数组不改变使用副本来进行操作
		ArrayList<String> collection11 = (ArrayList<String>) new ArrayList<>().clone();
		
		//返回一个迭器
		Iterator<String> iterator  = collection1.iterator();
		//输出所有元素
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
 
		 
	}

}
