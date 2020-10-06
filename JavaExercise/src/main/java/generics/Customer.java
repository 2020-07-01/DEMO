package generics;

/**
 * @author :qiang
 * @date :2019/10/10 下午8:19
 * @description :泛型还可以用于内部类以及匿名内部类
 * @other :
 */
public class Customer {

    private static long counter = 1;
    private final long id = counter++;

    private Customer() {
    }

    public String toString() {
        return "Customer" + id;
    }


}
