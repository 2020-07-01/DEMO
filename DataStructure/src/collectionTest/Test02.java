package collectionTest;

import java.util.ArrayList;
import java.util.List;

public class Test02 {
	public static void main(String[] args) {
		  List<Integer>  Listlist1 = new ArrayList<Integer>();
	      Listlist1.add(0);
	      List<Integer> Listlist2 = Listlist1;
	        System.out.println(Listlist1.get(0) instanceof Integer);
	        System.out.println(Listlist2.get(0) instanceof Integer);
	        
	        //默认的浮点类型是double
//	        float a = 3.14f;
	       String[] strings = new String[10];
	       System.out.println(strings.length);
	       System.out.println(strings[0]);
	       
	       String string = "fmnm";
	       String vString = string.toUpperCase();
	       System.out.println(vString);
	}
	
}
