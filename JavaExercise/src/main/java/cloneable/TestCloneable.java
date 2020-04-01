package cloneable;

import java.util.ArrayList;
 
/**
 * @ClassName:  TestCloneable   
 * @Description:测试cloneable
 * @author: caiji
 * @date: 2019年5月5日 下午3:12:21
 */
public class TestCloneable {

	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>(123);
		//创建新的对象
		ArrayList<Integer> arrayList2 = (ArrayList<Integer>) arrayList.clone();
		
		
		//内容相同的不同对象,比较的是引用
		System.out.println(arrayList == arrayList2);
		//比较的是内容
		System.out.println(arrayList.equals(arrayList2));
		
	}

}
