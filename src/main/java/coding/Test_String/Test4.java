package coding.Test_String;

/**
 * @ProjectName: JavaExercise
 * @ClassName: Test4
 * @Author: qiang
 * @Description: 统计一行字符串中存在多少个单词
 * @Date: 2019/6/27 下午3:07
 */
public class Test4 {

	//遇到空格加一，最后再加一
	//连续的若干个空格作为一个空格


	/**
	 * 如果一个字符为非空格，而他前面的字符为空格，则表示一个新的单词出现
	 *
	 * @param string
	 * @return
	 */
	public static int test1(String string) {

		char[] c = string.toCharArray();

		//统计单词数
		int count = 0;
		//表示此空格的前一个字符是否为空格
		int world = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				world = 0;

			}
			//此时此字符不是空格，要判断上一个字符是否为空格，如果是，则表示一个新的单词开始，并将world置为1，单词数加1
			else if (world == 0) {
				world = 1;
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		System.out.println(test1(" hello  world we are welcome you !"));
	}
}
