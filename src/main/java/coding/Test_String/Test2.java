package coding.Test_String;

import java.util.Arrays;

/**
 * @ProjectName: JavaExercise
 * @ClassName: Test2
 * @Author: qiang
 * @Description: 判断两个字符串是否由相同的字符组成
 * @Date: 2019/6/27 下午1:54
 */
public class Test2 {

	/*
	 * 由相同字符组成的字符串：字符相同和个数相同
	 */

	/*
	 *方法一：先对两个字符串进行排序，比较排序后的字符串是否相同
	 */
	public static Boolean isSame1(String string1, String string2) {

		//将字符串转换为字符数组进行排序
		char[] c1 = string1.toCharArray();
		char[] c2 = string2.toCharArray();

		Arrays.sort(c1);
		Arrays.sort(c2);

		//将排序后的字符数组转换为字符串
		String s1 = new String(c1);
		String s2 = new String(c2);

		//进行比较
		if (s1.equals(s2))
			return true;

		else
			return false;
	}

	/**
	 * 方法二：假设字符串中只有ASCII字符，创建大小为256的数组，遍历第一个字符串将字符的ASCII值作为数组的下标
	 * 然后值加1，再遍历第二个数组，将其对应的值减1
	 * 最后判断数组大小是否为0
	 */

	public static boolean isSame2(String string1, String string2) {
		//创建数组
		int[] count = new int[256];

		//将字符串转换为字符数组
		char[] c1 = string1.toCharArray();
		char[] c2 = string2.toCharArray();

		//遍历第一个字符数组
		for (int i = 0; i < c1.length; i++) {
			count[c1[i]]++;
		}

		//遍历第二字符数组
		for (int i = 0; i < c2.length; i++) {
			count[c2[i]]--;
		}

		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				return false;
			}
		}
		return true;
	}


	public static void main(String[] args) {

		System.out.println(isSame2("fafasfafas", "oehll"));
	}
}
