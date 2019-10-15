package container;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/15 下午9:48
 * @description :
 * @other :
 */
public class Unsupported {

    static void test(String msg, List<String> list) {
        System.out.println("-----msg------");
        Collection<String> c = list;
        Collection<String> sublist = list.subList(1, 8);//返回一个List

        //复制操作
        Collection<String> c2 = new ArrayList(sublist);

        //保留此列表中包含在指定集合中的元素
        /**
         * 此时会有异常抛出
         * 因为：参数list是由Arrays.asList()方法返回的一个固定的列表
         * 这个列表仅支持那些不会对数组大小改变的操作
         * retainAll()方法会对列表的底层数据结构的大小进行修改，因此会抛出异常
         */
        try {
            c.add("1");//扩展列表的大小，则抛出异常
        } catch (Exception e) {
            System.out.println(e);
        }

        /**
         * 对于未获得支持的操作的解决方法
         * 将Arrays.aList()产生的list作为参数传入给任何Collection中使它变为允许所有操作方法的容器
         */
        Collection<String> collection = new ArrayList(list);
        System.out.println(collection);
        collection.add("1");
    }

    public static void main(String[] args) {

        //返回由指定数组支持的固定大小的列表
        List<String> list = Arrays.asList("A,B,C,D,E,F,G,H,I,J,K,L,M,N".split(","));

        System.out.println(list.size());

        test("msg", list);
    }

}
