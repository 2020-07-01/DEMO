package dataStructure.find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.print.attribute.IntegerSyntax;

/**
 * @ClassName:  FullPermutation   
 * @Description:全排列的实现
 * 	对字符串进行有重复的全排列
 * 	字典序的排列结果为升序
 * @author: caiji
 * @date: 2019年4月13日 上午10:55:18
 */
public class FullPermutation {

	//对字符串进行全排列
	public static void fullPermutation(String str) {

		//如果字符串为空或长度为0，则结束语句的执行
		if (str == null || str.length() == 0) {
			return;
		}
		//保存所有的全排列
		ArrayList<String> list = new ArrayList<String>();
		//进行排列
		fullpermutation(str.toCharArray(), 0, list);
		//打印全排列
		print(list);
	}

	//打印全排列
	public static void print(ArrayList<String> list) {
		//按照字典序列输出
		Collections.sort(list);

		for (String string : list) {
			System.out.println(string);
		}

	}

	//递归进行全排列
	public static void fullpermutation(char[] array, int start, ArrayList<String> list) {

		//设置递归终止条件
		if (start == array.length - 1) {
			list.add(String.valueOf(array));//字符串的形式进行存储
		}

		else {
			for (int i = start; i <= array.length - 1; i++) {
				//固定第一个元素，分别与后面的元素进行交换
				swap(array, i, start);
				//递归求出全排列
				fullpermutation(array, start + 1, list);
				//复位
				swap(array, start, i);
			}
		}

	}

	public static void swap(char[] a, int i, int j) {
		char tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args) {
		String string = "12";

		int[] a = { 321 };

	}
}
