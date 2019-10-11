package array;

/**
 * @author :qiang
 * @date :2019/10/11 下午3:47
 * @description : 数组与泛型的学习，静态泛型方法与非静态泛型方法
 * @other :
 */
public class ClassParameter<T> {

    /**
     * 数组与泛型不能很好的结合
     * 不能实例化具有参数化类型的数组
     */
    //Object<String>[] p = new Object<Integer>[10];

    /**
     * 可以参数化数组本身的类型
     * 非静态方法可以使用类的类型也可以使用方法自己定义的类型
     *
     * @param args
     * @return
     */
    public T[] f(T[] args) {
        return args;
    }

}

class MethodParameter<T> {//此处的T可有可无

    /**
     * 静态方法随着类的加载而初始化，不能使用类的类型
     * 所以在创建时要指定方法的类型，在static后面加<T>,指定类型
     *
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T[] f(T[] args) {
        return args;
    }
}

