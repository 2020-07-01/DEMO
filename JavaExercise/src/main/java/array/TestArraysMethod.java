package array;

import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/19 下午7:58
 * @description : 学习静态类Arrays
 * @other :
 */
public class TestArraysMethod {

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        //将数组a中的元素全部复制到数组b中，次数会创建新的数组
        int[] b = Arrays.copyOf(a, a.length*2);
        //对数组进行修改
        a[0] = 0;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
