package nowcoder;

import java.util.Scanner;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

/**
 * 字符串进行排序 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 * 
 * 如，输入： Type 输出： epTy
 * 
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 * 
 * 如，输入： BabA 输出： aABb
 * 
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 * 
 * 如，输入： By?e 输出： Be?y
 * 
 * @author qiang
 *
 */
public class StringSort {
	
	public static String sort(String string) {
		
		char[] c = string.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		//先对字符串进行排序
		for(int i = 0;i < 26;i++)
		{
			for(int j = 0;j<c.length;j++)
			{//遍历c进行排序
				if(c[j] == 65+i || c[j] == 97+i)
					stringBuffer.append(c[j]);
			}
		}
		StringBuffer stringBuffer2 = new StringBuffer();
		
		//排序前b-dofgac
		//排序后abcdfgo
		
		int flag = 0;
		for(int i = 0;i<string.length();i++)
		{
			 
			if(isChar(c[i]))
			{//如果为字符就添加
				stringBuffer2.append(stringBuffer.charAt(flag));
				flag++;
			}
			else//如果不为字符串就将c中的当前元素添加
				stringBuffer2.append(c[i]);
		}
		
		return stringBuffer2.toString();
	}
	
	//判断某个字符是为为字母
	public static boolean isChar(char a) {
		if(a >= 65 && a <= 90)
			return true;
		else if(a >= 97  && a <= 122)
			return true;
		else
			return false;
	}
	
	
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String s = input.nextLine();
            String r = sort(s);
            System.out.println(r);
        }
    }
}
