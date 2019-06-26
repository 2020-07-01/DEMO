package thread;

/**
 * @ProjectName: JavaExercise
 * @ClassName: TestL
 * @Author: qiang
 * @Description:
 * @Date: 2019/6/26 下午10:49
 */
public class TestL {


	public static void main(String... args)

	{

		Runnable r2 = () -> System.out.println("Howdy, world!");

		r2.run();

	}

}