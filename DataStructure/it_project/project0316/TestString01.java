package project0316;

import java.util.Scanner;

/**
 * 判断两个字符串是否为变形字
 * @author qiang
 *
 */
public class TestString01 {
	public static void main(String[] args) {
	 
	 
	System.out.println("输入两个字符串：");
	Scanner input = new Scanner(System.in);
	String string1 = input.next();
	String string2 = input.next();
 	char[] c1 = string1.toCharArray();
	char[] c2 = string2.toCharArray();
	
	int[] map = new int[256];
	
	
	if(string1 != null && string2 != null)
	{
		if(string1.length() != string2.length())
		{
			System.out.println("false");
		}
		
		else
		{
			for(int i = 0;i<c1.length;i++)
			{
				map[c1[i]] = map[c1[i]] + 1;//map数组中保存的是字符出现的次数
			}
			
			for(int i = 0;i<c2.length;i++)
			{
				 map[c2[i]] = map[c2[i]]-1; 
			}
			
			
			int sum = 0;
			for(int i = 0;i<c2.length;i++)
			{
				sum = sum + map[c2[i]];
			}
			
			if(sum == 0)
				System.out.println("true");
			else
				System.out.println("false");
			 
		}
	}
	}
			
}
