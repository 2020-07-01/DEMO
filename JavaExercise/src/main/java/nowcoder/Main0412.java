package nowcoder;

import java.util.Stack;

/**
 * @ClassName: Mian0412
 * @Description:TODO
 * @author: caiji
 * @date: 2019年4月12日 上午10:20:16
 */
public class Main0412 {

	/*
	 * 
	 * @Description:输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
	 * @param pushA 1,2,3,4,5
	 * @param popA 4,5,3,2,1
	 * @return
	 */
	public boolean IsPopOrder(int[] pushA, int[] popA) {
		if (pushA.length == 0 || popA.length == 0)
			return false;

		Stack<Integer> stack = new Stack<Integer>();
		//用于标识弹出序列的位置
		int popIndex = 0;
		for (int i = 0; i < pushA.length; i++) {
			stack.push(pushA[i]);
			//如果栈不为空，且栈顶元素等于弹出序列
			while (!stack.empty() && stack.peek() == popA[popIndex]) {
				//出栈
				stack.pop();
				//弹出序列向后一位
				popIndex++;
			}
		}
		return stack.empty();
	}

	/*
	 * @Description: 求整数1到n中1出现的次数
	 *    解题思路：将数字转化为字符串，然后遍历字符串，如果遇到1，则count加1
	 * @param n
	 * @return
	 */
	public static int NumberOf1Between1AndN_Solution(int n) {

		String string;
		//计数
		int count = 0;

		for (int i = 0; i <= n; i++) {
			string = String.valueOf(i);
			for (int j = 0; j < string.length(); j++) {
				if (string.charAt(j) == '1') {
					count++;
				}
			}
		}
		return count;
	}

	/*
	 * @Description: 左旋转字符串位数
	 * @param str
	 * @param n
	 * @return
	 */
	public static String LeftRotateString(String str, int n) {

		//如果字符串长度小于2
		if (str.length() <= 1)
			return str;

		String str2 = str + str;
		char[] string = str2.toCharArray();

		String str3 = new String();
		for (int i = n; i < str.length() + n; i++) {
			str3 = str3 + string[i];
		}

		return str3;
	}

	/**
	 * 
	 * @ClassName: Solution
	 * @Description:定义一个栈的数据结构，在该栈中定义一个可以得到栈中最小数据的min函数
	 * @author: caiji
	 * @date: 2019年4月12日 上午11:19:36
	 */
	public class Solution {

		Stack<Integer> stack = new Stack<Integer>();

		/*
		 * @Description:添加元素 
		 * @param node
		 */
		public void push(int node) {
			stack.push(node);
		}

		/*
		 * @Description:删除栈顶的元素
		 */
		public void pop() {
			stack.pop();
		}

		/*
		 * @Description: 查看栈顶的元素
		 * @return
		 */
		public int top() {
			return stack.peek();
		}

		public int min() {
			//遍历栈，然后再入栈
			Stack<Integer> st = new Stack<Integer>();

			int min = 0;//最小的元素

			while (!stack.empty()) {
				if (stack.peek() < min) {
					min = stack.peek();
				}
				st.push(stack.pop());
			}

			while (!st.empty()) {

				stack.push(st.pop());
			}
			return 0;
		}
	}

	/*
	 * @Description:  
	 * @param args
	 */
	public static void main(String[] args) {

		String string = "asdfgh";
		System.out.println(LeftRotateString(string, 1));

	}
}
