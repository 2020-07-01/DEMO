package nowcoder;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author qiang
 *
 */
public class Exercise0404 {
	// 将一个数组中的奇数与偶数进行排序
	public static void reOrderArray(int[] array) {

		ArrayList<Integer> odd = new ArrayList<Integer>();
		ArrayList<Integer> oven = new ArrayList<Integer>();
		if (array.length == 0)
			return;

		int j = 0, k = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0)
				oven.add(array[i]);
			else {
				odd.add(array[i]);
			}
		}

		int h = 0;
		for (int i = 0; i < odd.size(); i++) {
			array[h++] = odd.get(i);
		}
		for (int i = 0; i < oven.size(); i++)
			array[h++] = oven.get(i);
	}

	// 打印最后一个单词的长度
	public static int printWorld(String string) {

		if (string.length() == 0)
			return 0;

		char[] cs = string.toCharArray();

		Stack<Character> Stack = new Stack<Character>();

		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == ' ') {
				while (!Stack.isEmpty())
					Stack.pop();
				Stack.push(cs[i]);
			} else
				Stack.push(cs[i]);

		}

		return Stack.size() - 1;

		/*
		 * int j = string.length(); for (int i = string.length() - 1; i >= 0; i--) {
		 * 
		 * if (string.charAt(i) == ' ') break; else j--; }
		 * 
		 * return string.length() - j;
		 */
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 4, 6, 8, 9, 5, 4, 2, 4, 5, 6, 7745, 5, 2 };
		reOrderArray(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}

		System.out.println();
		Scanner input = new Scanner(System.in);
		String string = input.nextLine();
		int p = printWorld(string);
		System.out.println(p);

		input = new Scanner(System.in);
		String s = "";
		while (input.hasNextLine()) {// 如果有下一行
			s = input.nextLine();
			System.out.println(s.length() - 1 - s.lastIndexOf(" "));// 返回指定子符最后一次出现在字符床中的索引
		}
	}
}
