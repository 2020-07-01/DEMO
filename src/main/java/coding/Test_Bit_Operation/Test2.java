package coding.Test_Bit_Operation;

/**
 * @ProjectName: JavaExercise
 * @ClassName: Test2
 * @Author: qiang
 * @Description: 如何判断一个数是否为2的n次方
 * @Date: 2019/6/27 下午7:02
 */
public class Test2 {

	/**
	 * @param n:给定数n
	 * @return
	 */
	//方法：指定i = 1;每次将i的值左移一位，与n进行比较
	public static boolean isPower(int n) {
		if (n < 1) {
			return false;
		}

		int i = 1;
		while (i <= n) {
			if (i == n) {
				return true;
			}

			i = i<<1;
		}
		return false;
	}


	public static void main(String[] args) {


		System.out.println(isPower(64));
	}
}
