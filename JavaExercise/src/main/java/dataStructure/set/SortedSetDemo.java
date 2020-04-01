package dataStructure.set;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author :qiang
 * @date :2019/10/16 上午11:08
 * @description : 测试TreeSet类
 * @other :
 */
public class SortedSetDemo {
    public static void main(String[] args) {
        /**
         * TreeSet的底层实现是红黑树
         * 是按对象的比较函数对元素进行排序，而不是按插入的顺序对元素进行排序
         */
        SortedSet<String> sortedSet = new TreeSet<String>();
        //对sortedSet进行赋值
        Collections.addAll(sortedSet, "one,two,three,four,five,sex,seven,eight".split(","));

        System.out.println(sortedSet);

    }
}
