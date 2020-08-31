package designMode.singlePattern;

/**
 * @author yq
 * @date 2020/8/22 14:30
 * 饿汉式
 */
public class Singleton1 {

    /**
     * 1、资源浪费
     * 2、通过反射可破解
     */
    private static Singleton1 instance1 = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {

        return instance1;
    }

    public void print() {
        System.out.print("单例模式不能传入参数");
    }

    public static void main(String[] args) throws Exception{
        Singleton1 singleton1 = new Singleton1();
        Singleton1 singleton12 = Singleton1.class.newInstance();

        System.out.println(singleton1);
        System.out.println(singleton12);
    }
}
