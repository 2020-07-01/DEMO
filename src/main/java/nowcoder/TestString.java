package nowcoder;

import java.io.FileFilter;

public class TestString {
	public int findAppearance(String A, int lena, String B, int lenb) {

		int p = A.indexOf(B);

		return p;

	}

	public static String formatString(String A, int n, char[] arg, int m) {
		int i = 0;

		while (A.indexOf("%s") > -1) {
			A = A.replaceFirst("%s", String.valueOf(arg[i]));
			i++;
		}

		while (i < m) {
			A = A + arg[i];
			i++;
		}
		return A;

	}

	public String replaceSpace(String iniString, int length) {

		// write code here
		StringBuilder sb = new StringBuilder();
		String strReplace = "%20";
		for (int i = 0; i < length; i++) {
			char tmp = iniString.charAt(i);
			if (tmp == ' ') {
				sb.append(strReplace);
			} else {
				sb.append(tmp);// 重新构造stringbuffer
			}
		}
		return sb.toString();
	}

	// 反转字符串，倒叙输出
	public String reverseString(String iniString) {
		StringBuffer stringBuffer = new StringBuffer();

		int i = iniString.length() - 1;// 字符串的下表从0开始
		while (i >= 0) {
			stringBuffer.append(iniString.charAt(i));// 返回一个字符添加到stringbuffer后面
			i--;
		}

		return stringBuffer.toString();
	}

	// s2是否为s1旋转而来
	public boolean checkReverseEqual(String s1, String s2) {

		int len = s1.length();
		if (len == s2.length() && len > 0) {
			String s1s1 = s1 + s1;// 此时中间包含s2
			return s1s1.contains(s2);
		}
		return false;
	}

	// 确定字符串中的字符是否互异
	public boolean checkDifferent(String iniString) {

		char a;

		for (int i = 0; i < iniString.length() - 1; i++) {
			a = iniString.charAt(i);
			if (a == iniString.charAt(i + 1))
				return false;
		}
		return true;
	}

	// 字符串的旋转
	public String rotateString(String A, int n, int p) {

		// p指的是下标的位置
		String s1 = A.substring(0, p + 1);
		String s2 = A.substring(p + 1, n);
		return s2 + s1;

	}

	// 约瑟夫问题
	public int getResult(int n) {
		return ysf(n, 2);
	}

	public int ysf(int n, int m) {
		int tmp = n % m == 0 ? n / m : n / m + 1;
		if (tmp <= m + 1) {
			return (tmp - 1) * m + 1; // 终止条件
		}
		int path = ysf(tmp, m + 1); // m+1次时最后一人编号的位置
		return (path - 2) * m + 1;
	}

	// 将一个二维矩阵之字形打印
	public int[] printMatrix(int[][] mat, int n, int m) {

		// 新建数组
		int[] newarray = new int[n * m];

		int k = 0;
		int hang = 0;
		for (int i = 0; i < mat.length; i++) {

			if (hang % 2 == 0) {
				for (int j = 0; j < mat[i].length; j++) {
					newarray[k++] = mat[i][j];
				}
			}
			if (hang % 2 == 1) {
				for (int j = mat[i].length - 1; j >= 0; j--) {
					newarray[k++] = mat[i][j];
				}

			}
			hang++;
		}
		return newarray;
	}

	// 返回无序数组的相邻元素的最大差值
	public int findMaxDivision(int[] A, int n) {

		int max = A[1] - A[0];
		int k = 0;
		for (int i = 1; i < n; i++) {
			int t = A[i] - A[i - 1];
			if (t > 0 && t > max) {
				max = t;
			}
			if (t < 0) {
				t = A[i - 1] - A[i];
				if (t > max) {
					max = t;
				}
			}

		}
		return max;
	}

	// 测试
	public static void main(String[] args) {
		TestString test01 = new TestString();
//		String initString = "fa fagrre rew w";
		/*
		 * String s1 = "abdccc"; String s2 = "cba"; int p = test01.getResult(5);
		 * System.out.println(p);
		 */
		/*
		 * int[] a = { 1000, 200000, 43, 4, 5, 56, 6, 3, 32, 54533, 500 }; int p =
		 * test01.findMaxDivision(a, 11); System.out.println(p);
		 */

		char a = 'a';
		if (a == 1) {
			System.out.println(true);
		} else
			System.out.println(false);

		char b = 56;
		System.out.println(b);

	}

}
