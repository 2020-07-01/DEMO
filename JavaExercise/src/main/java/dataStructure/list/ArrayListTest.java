package dataStructure.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yq
 * @date 2020/7/21 19:34
 * ArrayList遍历方式及其性能比较
 */
public class ArrayListTest {

    public static void main(String[] args) {

        Set set = new HashSet();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for(int i = 1;i<=2000000;i++){
            arrayList.add(i);
        }


        /**
         * for循环遍历
         */
        Long startTime1 = System.currentTimeMillis();
        for(int i = 0;i<arrayList.size();i++){
            set.add(arrayList.get(i));
        }
        System.out.println("for遍历耗时："+(System.currentTimeMillis()-startTime1)+"mm");

        /**
         * foreach循环遍历
         * 不能操作元素，但是可以操作元素的属性
         */
        Long startTime2 = System.currentTimeMillis();
        set = new HashSet();
        for (Integer item : arrayList) {

            set.add(item);
        }
        System.out.println("foreach遍历耗时:"+(System.currentTimeMillis()-startTime2)+"mm");

        /**
         * lambda表达式遍历耗时
         */
        Long startTime3 = System.currentTimeMillis();
        set = new HashSet();
        for (Integer item : arrayList) {
            set.add(item);
        }
        System.out.println("lambda表达式遍历耗时:"+(System.currentTimeMillis()-startTime3)+"mm");

        /**
         * Iterator遍历耗时
         */
        Long startTime4 = System.currentTimeMillis();
        set = new HashSet();
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            set.add(iterator.next());
        }
        System.out.println("Iterator遍历耗时:"+(System.currentTimeMillis()-startTime4)+"mm");


        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);

        for (Integer item: arrayList1) {
            arrayList1.remove(item);
        }


    }
}
