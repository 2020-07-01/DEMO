package collectionTest;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * linkedHashSet扩展hashSet类，他支持对集合内的元素进行排序，可以按照他们插入集合的顺序进行提取
 * HashSet中的元素是没有进行排序的
 * 
 * TreeSet可以实现元素的排序
 * @author qiang
 *
 */
public class TestLinkedHashSet {
	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<>();
		set.add("qwe");
		set.add("tre");
		set.add("qr");
		set.add("ffds");
		set.add("hfggf");
		
		System.out.println(set);
		
		
		TreeSet<String> treeSet = new TreeSet<>(set);
		
		System.out.println("输出第一个元素："+treeSet.first());
		System.out.println("返回小于此元素的一部分,指的是此元素后面的一部分"+treeSet.headSet("qr"));
		
		
	}

}
