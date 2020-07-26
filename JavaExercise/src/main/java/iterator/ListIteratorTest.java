
package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author yq
 * @date 2020/7/26 14:48
 * list的迭代器学习
 */
public class ListIteratorTest {

    public static void main(String[] args) {

        /**
         *
         */
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            if(iterator.next().equals("3")){
             iterator.remove();
            }
        }

        /**
         * ListIterator
         * 默认从下标为0的元素处开始遍历
         */
        ListIterator<String> listIterator = arrayList.listIterator();
        //向前进行遍历
        while (listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }
        //向后进行遍历
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }


        iterator.forEachRemaining(p->{
            System.out.println(p);
        });


    }



}
