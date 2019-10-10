package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/10 下午8:06
 * @description :泛型方法与可变参数列表
 * @other :
 */
public class GenericVarargs {

    public static <T> List<T> makelist(T... args) {

        List<T> result = new ArrayList<T>();

        for (T item : args)
            result.add(item);

        return result;
    }

    public static void main(String[] args) {

        List<String> ls = makelist("A");
        System.out.println(ls);

        List<String> ls1 = makelist("A", "B", "C");
        System.out.println(ls1);
    }

}
