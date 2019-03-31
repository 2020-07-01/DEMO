package nowcoder;

import java.util.Scanner;

/**
 * 实现斐波那契数列
 * 
 * @author qiang
 *
 */

public class Sequence {
	public static int Fibonacci(int n) {
		int a = 1, b = 1, c = 0;

		if (n <= 0)
			return 0;
		else if (n == 1 || n == 2)
			return 1;
		else {
			for (int i = 3; i <= n; i++) {
				c = a + b;
				a = b;
				b = c;
			}
		}
		return c;

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();
		int p = Fibonacci(x);
		System.out.println(p);
	}
}
