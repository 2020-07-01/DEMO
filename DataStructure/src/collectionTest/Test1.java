package collectionTest;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		
		int x1 = 1;
		int x2 = 4;
		int x3 = 16;
		int x4 = 64;
	 
		 
		
		System.out.println("输入N：");
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		
		int p = 1024-N;
		
		int a = 0;
		if(p>=64) {
			while(p>=64) {
				p = p-64;
				a++;
			}
		}
		
		int b = 0;
		
		if(p>=16 && p< 64)
		{
			while(p>=16) {
				p = p-16;
				b++;
			}
		}
		
		int c = 0;
		if(p>= 4 && p<16)
		{
			while(p>=4) {
				p = p -4;
				c++;
			}
		}
		
		int d = 0;
		if(p>0 && p<4)
		{
			while(p>0) {
				p = p-1;
				d++;
			}
		}
		
		int total = a+b+c+d;
		System.out.println(total);
	}

}
