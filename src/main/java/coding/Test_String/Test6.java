
package coding.Test_String;

import sun.plugin.navig.motif.OJIPlugin;

import javax.lang.model.element.NestingKind;
import java.io.Console;
import java.math.BigInteger;
import java.util.*;

/**
 * @ProjectName: JavaExercise
 * @ClassName: Test6
 * @Author: qiang
 * @Description:
 * @Date: 2019/6/30 下午8:28
 */
public class Test6 {

	public static void main(String[] args) {
		System.out.println("Hello".substring(0, 3));
		System.out.println(String.join("+", "1", "2", "3"));

		String string = "";
		System.out.println(string.length());
		System.out.println();

		/*

		Scanner input = new Scanner(System.in);
		String m = input.next();
		switch (m)
		{
			case "1" :
				System.out.println(1);break;
			case "2":
				System.out.println(2);break;
			default:
				System.out.println("exit");
		}
        */

		/*
		ArrayList<String> list = new ArrayList<String>();

		System.out.println("输入：");

		Scanner sc = new Scanner(System.in);


		while (sc.hasNext()) { // 判断是否结束
			String s = sc.nextLine();// 读入整数
			list.add(s);

		}
		for(String e : list){
			System.out.println(e);
		}

        */

        /*
break:直接结束循环体，后面的程序继续执行
continue:跳到循环的首部
return:直接结束整个程序，后面的程序将不会执行
 */

		int k = 0;
		while (k < 10) {
			k++;
			if (k == 4)
				//continue;//直接跳到循环体的首部
				break;


			System.out.println(k);
		}


		BigInteger a = BigInteger.valueOf(100);

		BigInteger b = BigInteger.valueOf(2);

		BigInteger c = a.add(b);

		System.out.println(c);


		int[] array = new int[100];
		for (int array1 : array) {
			System.out.println(array1);
		}

		System.out.println(array.length);

		Object[] objects = new Object[10];
		for (Object o : objects) {
			System.out.println(o);
		}

		System.out.println(Arrays.toString(array));

		//创建长度为0的数组
		int[] e = new int[0];
		int[] lunck = new int[10];


		//数组变量之间的拷贝，此时两个数组的引用值相同
		int[] aa = lunck;

		System.out.println(Arrays.toString(args));

		//打印三角型
		final int NMAX = 10;
		//创建一个具有10行的数组
		int[][] adds = new int[NMAX][];
		//给每一行分配大小
		for (int i = 0; i < NMAX; i++) {
			adds[i] = new int[i + 1];
		}

		//填充数组
		for (int n = 0; n < adds.length; n++) {
			for (int h = 0; h < adds[n].length; h++) {
				adds[n][h] = 1;
			}
		}

		//输出数组
		System.out.println(Arrays.deepToString(adds));
		System.out.println();
		for (int[] row : adds) {
			for (int add : row) {
				System.out.print(add + "  ");
			}
			System.out.println();
		}


		int[] a1 = new int[3];
		a1[0] = 0;
		a1[1] = 1;
		a1[2] = 2;

		int[] a2 = a1;

		System.out.println("输出a1：");
		for(int c1 : a1)
		{
			System.out.println(c1);
		}
		System.out.println("输出a2:");
		for(int c2 : a2)
		{
			System.out.println(c2);
		}

		System.out.println("改变a1的值:a1[0] = 10");
		a1[0] = 10;

		System.out.println("再次输出a1：");
		for(int c1 : a1)
		{
			System.out.println(c1);
		}
		System.out.println("再次输出a2:");

		for(int c2 : a2)
		{
			System.out.println(c2);
		}





	}

}
