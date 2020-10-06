package designMode.singlePattern;

/**
 * @ClassName : SingleDemo1
 * @Author : yq
 * @Date: 2020-08-30
 * @Description :懒汉式单例模式
 */
public class Singleton2 {

    private String name;
    //私有化构造方法
    private Singleton2() {
        this.name = "single";
    }

    /**
     * 两种理解思路：
     * 第一种：
     * singleton2为null,线程A获取锁并对singleton2进行初始化
     * 当对singleton2进行了赋值，还没有对name进行赋值
     * 此时线程B检测到singleton2不为null，随即获取到实例对象
     * 但是对象的name值为null，并不为single   此时出现线程安全问题
     *
     * 第二种：
     * 对象的创建分为若干个步骤
     * 在分配完内存后将指针指向栈，但还没有初始化时
     * 此时会出现获取不到对象的情况
     */


    //避免指令重排 使对象的创建过程按步骤进行
    private  volatile static Singleton2 singleton2;

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

        System.out.println(instance1);

    }
}
