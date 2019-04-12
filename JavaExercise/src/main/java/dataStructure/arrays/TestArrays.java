package dataStructure.arrays;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @ClassName: TestArrays
 * @Description:测试Arrays类 Arrays类是Java API中提供的类，在Java.util包中，此类包含用来操作数组的各种方法。
 * @ Arrays类是不能被实例化的，是单例模式
 * @author: caiji
 * @date: 2019年4月12日 下午7:21:15
 */
public class TestArrays {

	public static void main(String[] args) {

		/*
		 * aslist()：该方法返回的是内部静态类ArrayList，将数组转换为集合，接受一个可变参数
		 */
		Integer[] data = { 1, 2, 3, 4, 5 };
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(data));
		//对可迭代的每个元素执行给定的操作，直到处理完所有的元素或者发生异常为止
		list.forEach(System.out::print);//lambda的缩写形式
		System.out.println("\n" + "lambda表达式：");
		list.forEach(e -> System.out.print(e));//lambda表达式

		/*
		 * fill()：用指定的元素填充整个数组(会替换掉数组中原来的元素)
		 * 用指定的元素填充数组，从起始位置到结束位置，取头不取尾
		 * fill(Object[] array, int fromIndex, int toIndex, Object obj)
		 */
		System.out.println("\n" + "fill()方法：");
		Integer[] data1 = { 1, 2, 3, 4 };
		Arrays.fill(data1, 9);//用9填充data数组
		System.out.println(Arrays.toString(data1));//打印数组
		Arrays.fill(data1, 0, 2, 7);//用9填充data数组中0，1位置的元素
		System.out.println(Arrays.toString(data1));

		/*
		 * sort()：对元素进行排序（串行排序）
		 */
		System.out.println("sort()方法：");
		String[] data2 = { "1", "5", "3", "87" };
		System.out.println(Arrays.asList(data2).toString());
		Arrays.sort(data2);//进行穿行排序，结果为升序
		System.out.println(Arrays.asList(data2).toString());

		System.out.println("使用自定义的比较器进行排序：");

		String[] data3 = { "1", "5", "3", "87", "0" };
		//实现降序排序，返回-1放左边，1放右边，0保持不变
		Arrays.sort(data3, (str1, str2) -> {
			if (str1.compareTo(str2) > 0)
				return -1;
			else
				return 1;
		});
		System.out.println(Arrays.asList(data3).toString());

		System.out.println("对数组内指定范围内的元素进行排序：");
		//对数组内下标为1，2，的元素进行排序，结果为升序
		Arrays.sort(data3, 1, 3);
		System.out.println(Arrays.asList(data3).toString());

	}

}
