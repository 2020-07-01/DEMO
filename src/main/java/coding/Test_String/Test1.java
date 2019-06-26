package coding.Test_String;

/**
 * @ProjectName: JavaExercise
 * @ClassName: Test1
 * @Author: qiang
 * @Description: 将一个句子中的单词进行反转
 * @Date: 2019/6/26 下午9:38
 */
public class Test1 {

	public String swapWorlds(String s) {
		//首先将字符串转换为字符数组
		char[] c = s.toCharArray();

		//然后对字符数组进行反转
		swap(c, 0, c.length - 1);

		//然后对字符串中的单词进行反转
		int begin = 0;
		for (int i = 1; i < c.length; i++) {
			//如果遇到空格则将前面的单词进行反转
			if (c[i] == ' ')
			{
				swap(c,begin,i-1);
				begin = i+1;
			}
		}

		//对最后一个单词进行反转
		swap(c,begin,c.length-1);

		return new String(c);
	}


	//对字符串进行反转
	public void swap(char[] c, int font, int end) {

		while (font < end) {
			char temp = c[font];
			c[font] = c[end];
			c[end] = temp;

			font++;
			end--;
		}

	}

	public static void main(String[] args) {

		String s = "hello world ";
		System.out.println(new Test1().swapWorlds(s));


	}
}
