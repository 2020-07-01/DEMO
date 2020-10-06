package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :qiang
 * @date :2019/10/11 下午2:26
 * @description :泛型数组
 * @other :
 */
public class ListOfGenerics<T> {

    private List<T> array = new ArrayList();

    public void add(T item) {
        array.add(item);
    }

    public T get(int index) {
        return array.get(index);
    }


    static final int SIZE = 10;
    static Generic<Integer>[] gia;


    public static void main(String[] args) {
        gia = (Generic<Integer>[])new Object[SIZE];
        System.out.println(gia.getClass().getName());


    }






}
