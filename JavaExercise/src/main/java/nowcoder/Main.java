package nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

/**
 * @ClassName:  Main   
 * @Description:京东笔试
 * @author: caiji
 * @date: 2019年4月13日 下午8:38:18
 */
public class Main {


/*请完成下面这个函数，实现题目要求的功能
当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^ 
******************************开始写代码******************************/
    static int string2int(String str) {
    	//转换为字符
    	char[] c = str.toCharArray();
    	ArrayList<Character> list = null;
    	
		//遍历查找为0的情况
		for (int i = 0; i < c.length; i++) {
			//如果有不是整数的字符，返回0
			if (c[i] > 57 || c[i] == 47 || c[i] < 46)
				return 0;
		}
		
		 
			for (int i = 0; i < c.length; i++) {
					if(c[i] != 0 && c[i+1] == 0)
					{
						
					}
			}
	 
		
		return 0;
		
		
		
 
    	
		/*else {
			//保存小数点前面的数字
			int i = 0;
			while(c[i] != 47)
			{
				cc[i] = c[i];
				i++;
			}
			
			int p = Integer.parseInt(cc.toString());
			return p;
		}*/
    	 
    }
/******************************结束写代码******************************/


    public static void main(String[] args){
    	System.out.println("shuru ");
        Scanner in = new Scanner(System.in);
        int res;
            
        String _str;
        try {
            _str = in.nextLine();
        } catch (Exception e) {
            _str = null;
        }
  
        res = string2int(_str);
        System.out.println(String.valueOf(res));    

    }
}
