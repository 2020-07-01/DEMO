package collectionTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	       
	       
	       //HashSet默认是数组实现
	       HashSet<Integer> hashSet = new HashSet<Integer>();
	       hashSet.add(0);
	       
	       //LinkedHashSet是链表实现
	       Set set = new LinkedHashSet<>();
	       
	       
	       Map map = new HashMap<>();
 
	}
	
}
