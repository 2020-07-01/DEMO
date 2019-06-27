package coding.Test_String;

/**
 * @ProjectName: JavaExercise
 * @ClassName: Test3
 * @Author: qiang
 * @Description: 删除字符串中重复的字符
 * @Date: 2019/6/27 下午2:28
 */
public class Test3 {


	/**
	 * 方法一：遍历字符串数组，每一个字符都对应一个ASCII码值
	 * 创建长度为256的数组，以每个字符的ASCII值为下标，
	 * count数组初始时值为0，如果字符第一次出现则将字符所对应下标的值加1
	 * 如果第二次出现时值为1，则说明此字符串出现过，则将此字符设置为\0
	 * 最后删除\0
	 */

	public static String delete(String string) {

		char[] c = string.toCharArray();

		int[] count = new int[256];

		for (int i = 0; i < c.length; i++) {
			if (count[c[i]] == 1) {
				c[i] = '\0';
			} else
				count[c[i]] = 1;
		}

		//删除\0
		int j = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != '\0')
				//重置c数组
				c[j++] = c[i];
		}

		//将不重复的字符部分转换为字符串
		String s = new String(c, 0, j);

		return s;
	}


	/**
	 * 方法二：使用双重遍历数组法，如果数组中存在多个相同的字符，则将后面的字符设置为\0最后删除\0
	 */
	public static String delete2(String string) {

		char[] c = string.toCharArray();
		for (int i = 0; i < c.length; i++) {
			char temp = c[i];

			for (int j = i + 1; j < c.length; j++) {
				if (c[j] == temp) {
					//如果存在第二个则置为0
					c[j] = '\0';
				}
			}
		}

		//最后删除\0重置数组
		int k = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != '\0')
				c[k++] = c[i];
		}

		String s = new String(c, 0, k);
		return s;
	}

	public static void main(String[] args) {

		System.out.println(delete2("abasafasvagasfeacdefgaaa"));
	}
}
