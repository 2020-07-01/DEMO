package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/11 下午4:06
 * @description :
 * @other :
 */
public class ArrayOfGenerics {

    public static void main(String[] args) {

        List<String>[] ls;//创建泛型数组的引用

        List[] la = new List[10];//创建数组

        ls = (List<String>[]) la;//进行数组引用的赋值并进行类型的转换

        ls[0] = new ArrayList<String>();//存储String类型的对象

        //ls[1] = new ArrayList<Integer>();

        Object[] obejects = ls;
        obejects[1] = new ArrayList<Integer>();

        int[] a = new int[10];
        Arrays.fill(a, 1);//用一个值填充各个位置
        for (int item : a) {
            System.out.println(item);
        }
    }
}
