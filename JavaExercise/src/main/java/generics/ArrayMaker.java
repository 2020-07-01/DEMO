package generics;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author :qiang
 * @date :2019/10/10 下午9:09
 * @description :边界处的动作
 * @other :
 */
public class ArrayMaker<T> {

    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {

        ArrayMaker<String> string = new ArrayMaker<String>(String.class);

        String[] strings = string.create(9);
        System.out.println(Arrays.toString(strings));
    }

}
