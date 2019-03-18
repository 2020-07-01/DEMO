package project0318;

import java.util.Scanner;
import java.util.function.IntSupplier;

/**
 * 找到被指定的新型字母
 * 
 * @author qiang
 *
 */
public class Test02 {
	/*
	 * 统计方法：
	 * 从k-1位置开始从左往右开始统计大写字母的数量，即为num
	 * 如果num为奇数，则输出k-1到k的字母
	 * 如果num为偶数，且k位置为大写字母，则输出k与k+1
	 * 如果num为偶数，且k位置为小写字母，则输出k位置字母
	 */
	public static String newstring(String s, int k) {
		if (s == null || s.equals("") || k < 0 || k >= s.length()) {
			return "";
		}
		char[] chas = s.toCharArray();
		int uNum = 0;
		for (int i = k - 1; i >= 0; i--) {
			if (!isUpper(chas[i])) {
				break;
			}
			uNum++;
		}
		if ((uNum & 1) == 1) {
			return s.substring(k - 1, k + 1);
		}
		if (isUpper(chas[k])) {
			return s.substring(k, k + 2);
		}
		return String.valueOf(chas[k]);
	}

	// 判断是否为大写字母
	public static boolean isUpper(char ch) {

		return !(ch < 'A' || ch > 'Z');
	}

	// 判断是否为小写字母
	public static boolean isLower(char ch) {

		return !(ch < 'a' || ch > 'z');
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("输入字符串：");
		String string = input.next();
		System.out.println("输入数字：");
		int k = input.nextInt();
		
		String p = newstring(string, k);
		System.out.println(p);

	}
}
