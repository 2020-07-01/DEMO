package collectionTest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 测试散列集hashSet
 * 散列集中的元素没有特定的顺序，不允许重复元素的出现
 * @author qiang
 *
 */
public class TestHashSet {
	public static void main(String[] args) {
		HashSet<String> hashSet = new HashSet<>();
		hashSet.add("123");
		hashSet.add("143");
		hashSet.add("153");
		hashSet.add("163");
		hashSet.add("173");
		
		
		System.out.println(hashSet);
		 
		for(String string : hashSet) {
			//将所有字母转换为大写
			System.out.println(string.toUpperCase());
		}
		
		
		System.out.println("测试二：");
		Set<String> set = new HashSet<>();
		set.add("longdong");
		set.add("tongdoffang");
		set.add("yongdofafsng");
		set.add("donsadgdong");
		set.add("gfdasongdong");
		
		//使用迭代器输出元素
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		
	}
}
