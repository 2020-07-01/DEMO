package designMode.singlePattern;

/**
 * @ClassName : SingleDemo1
 * @Author : yq
 * @Date: 2020-08-30
 * @Description :懒汉式单例模式
 */
public class Singleton2 {


    private Singleton2() {

    }

    //避免指令重排
    private volatile static Singleton2 singleton2;

    /**
     * 1、线程安全问题
     * 2、反射破坏
     * 3、new Singleton2   =》 分配内存空间，给调用构造方法初始化对象，栈指针指向堆
     */


    //DCL 双重检测锁模式
    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }

    public static void main(String[] args) throws Exception {

        Singleton2 instance1 = Singleton2.getInstance();
        //Constructor<Singleton2> constructor = Singleton2.class.getDeclaredConstructor(null);//获取一个空参构造器
        //constructor.setAccessible(true);
        //Singleton2 instance2 = constructor.newInstance();
        System.out.println(instance1);
        //System.out.println(instance2);
    }
}
