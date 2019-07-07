package coding.Test_Bit_Operation;

/**
 * @ProjectName: JavaExercise
 * @ClassName: TestInterface
 * @Author: qiang
 * @Description: 如何进行位运算
 * @Date: 2019/6/27 下午6:59
 */
public class Test1 {

	public static void main(String[] args) {

		//左移n位相当于乘以2的n次方
		System.out.println(3<<1);
		System.out.println(2<<3);

		//右移n为相当与除以2的n次方
		System.out.println(4>>1);
		System.out.println(128>>2);

	}
}
