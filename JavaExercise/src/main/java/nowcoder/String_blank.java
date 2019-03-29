package nowcoder;

import java.util.Scanner;

/**
 * 替换
 * @author qiang
 *
 */
public class String_blank {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		String string = input.nextLine();
		StringBuffer stringBuffer = new StringBuffer(string);
		
		String a = stringBuffer.toString().replaceAll(" ", "%20");
		
		System.out.println(a);
	}
	 

}
