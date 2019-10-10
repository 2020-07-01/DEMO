package generics;

/**
 * @author :qiang
 * @date :2019/10/10 下午7:38
 * @description :泛型方法
 * @other :
 * <p>
 * 一个类是否拥有泛型方法与所在的类是否为泛型没有关系
 * 如果使用泛型方法可以取代整个类的泛型化，那么就应该使用泛型方法
 */
public class GenericMethod {

    /**
     * 在使用泛型类时，在创建对象的时候要指定类型参数的值，
     * 而使用泛型方法的时候，通常不必指明参数类型，因为编译器会为我们找出指定的类型
     * 通常称为类型参数推断
     * @param x
     * @param <T>
     */
    public <T> void f(T x) {

        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();

        genericMethod.f("123");
        genericMethod.f("1");
        genericMethod.f("");
        genericMethod.f('c');
        genericMethod.f(1.0);
    }
}
