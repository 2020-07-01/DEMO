package nowcoder;

import java.util.Scanner;

/**
 * 字符传中空隔的替换
 * @author qiang
 *
 */
public class String_blank {
	public String replaceSpace(StringBuffer str) {
		
		String string = str.toString();
		String newString = string.replaceAll(" ", "%20");
		 
		return newString;
    }
	
	public static void main(String[] args) {
		String_blank test = new String_blank();
		Scanner input = new Scanner(System.in);
		String string = input.nextLine();
		StringBuffer stringBuffer = new StringBuffer(string);
		String newString = test.replaceSpace(stringBuffer);
		System.out.println(newString);
		
		
	}
}