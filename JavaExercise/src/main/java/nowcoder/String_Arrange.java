package nowcoder;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 
 * @author qiang
 *
 */
public class String_Arrange {

	public ArrayList<String> Permutation(String str) {
		// 设置返回类型
		ArrayList<String> result = new ArrayList<String>();

		if (str.length() == 0)// 如果字符串为空
			return result;
		char[] chars = str.toCharArray();
		TreeSet<String> res = new TreeSet<String>();// 用于排序输出

		return null;

	}

	// 交换字符数组中两个元素的位置
	public void swap(char[] chars, int a, int b) {
		if (a == b) {
			// 原位置与原位置互换，直接空即可
		} else {
			char temp = chars[a];
			chars[a] = chars[b];
			chars[b] = temp;
		}
	}
}