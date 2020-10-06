package generics;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/**
 * @author :qiang
 * @date :2019/10/10 下午8:28
 * @description :泛型类型擦除
 * @other :
 */
public class ErasedTypeEquivalence {


    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();


        Class class1 = list1.getClass();
        Class class2 = list2.getClass();
        System.out.println(class1 == class2);

        /**
         * 此数组中存储的是泛型声明所声明的参数
         * 这里存储的只是用作参数占位符的标识符
         * java中泛型是使用擦除来实现的，这意味着当在使用泛型的时候，任何具体的类型信息都会被擦除，你唯一知道的就是你在使用一个对象
         * 会被擦除为原生的类型
         */
        TypeVariable[] classes = class1.getTypeParameters();
        for (TypeVariable item : classes) {
            System.out.println(item);
        }
    }

}
