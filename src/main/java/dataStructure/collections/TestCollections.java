package dataStructure.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @ClassName: TestCollections
 * @Description:工具类Collections用于操作集合类，如list、set，所提供的方法都是静态的 Collectiosn和Arrays都是工具类，不能被实例化，采用的是单例模式
  *   单例模式：只有一个实例，必须是自己创建的自己的实例，
  *   单例类给其他对象提供这一实例，构造方法为private
 * @author: caiji
 * @date: 2019年4月12日 下午8:15:46
 */
public class TestCollections {

	public static void main(String[] args) {

		/*
		 * 	测试sort:根据元素的自然顺序对集合中的元素进行排序
		 */
		Integer[] array = { 1, 5, 2, 4, 53, 6, 7, 8 };

		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(array));
		//使用默认方法进行排序，结果为升序
		Collections.sort(list);
		System.out.println("对元素进行排序：也可以使用自定义的构造器");
		list.forEach(System.out::println);

		/*
		 * reverse():对集合中的元素进行反转
		 */
		String string = "a b c d e f";
		String[] string1 = string.split(" ");//此方法返回的是一个数组
		List<String> list1 = new ArrayList<String>(Arrays.asList(string1));
		System.out.println("打印字符串：");
		System.out.println(list1);
		System.out.println("打印反转后的字符串：");
		Collections.reverse(list1);
		System.out.println(list1);

		/*
		 * swap()：将list集合中的指定两个元素进行交换位置
		 */
		Integer[] integers = { 1, 2, 3, 4, 5, 6, 7 };
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(integers));
		//将索引为1和2处的元素进行交换
		Collections.swap(list2, 1, 2);
		System.out.println("交换后的元素：\n" + list2);

	}

}
