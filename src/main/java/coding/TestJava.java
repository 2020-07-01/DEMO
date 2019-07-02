package coding;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @ProjectName: JavaExercise
 * @ClassName: TestJava
 * @Author: qiang
 * @Description:
 * @Date: 2019/6/30 下午7:31
 */
public class TestJava {

	//设置一个静态变量
	static int a = 1;

	//创建静态常量
	static final int b = 4;

	public static void test() {
		System.out.println("static");
	}


	public static void main(String[] args) {

		System.out.println(LocalDate.now());

		LocalDate localDate = LocalDate.now();

		LocalDate localDate1 = localDate.plusDays(1);

		System.out.println(localDate);
		System.out.println("一天后的日历：" + localDate1);
		System.out.println(new Date().getMonth());

		System.out.println(a);

		a = 4;
		System.out.println(a);

		TestJava testJava = new TestJava();
		testJava.a = 5;
		System.out.println(a);

		TestJava testJava1 = new TestJava();
		testJava1.a = 6;
		System.out.println(a);


		TestJava.test();
		testJava1.test();
	}


}
