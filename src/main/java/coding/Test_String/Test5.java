package coding.Test_String;

/**
 * @ProjectName: JavaExercise
 * @ClassName: Test5
 * @Author: qiang
 * @Description: 如何输出字符串的所有组合
 * @Date: 2019/6/27 下午5:02
 */
public class Test5 {

	/**
	 * @param c:字符数组
	 * @param begin：
	 * @param len：开始下标
	 * @param sb：数组长度
	 */
	public static void combine(char[] c, int begin, int len, StringBuffer sb) {
		//如果长度为0则结束
		if (len == 0) {
			System.out.println(sb + " ");
			return;
		}

		//如果begin元素为数组的长度则直接结束
		if (begin == c.length) {
			return;
		}

		sb.append(c[begin]);

		combine(c, begin + 1, len - 1, sb);
		sb.deleteCharAt(sb.length() - 1);
		combine(c, begin + 1, len, sb);

	}


	public static void main(String[] args) {
		char[] arr = {'a', 'b', 'c'};

		StringBuffer supersb = new StringBuffer();
		for (int i = 0; i <= arr.length; i++) {
			combine(arr, 0, i, supersb);
		}
	}
}
