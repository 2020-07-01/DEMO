package designMode.singlePattern.demo1;

/**
 * @author yq
 * @date 2020/8/22 14:30
 *
 * 单例模式在于创建一个实例对象
 * 单例模式分为懒汉式和饿汉式
 *
 * 而懒汉式单例模式，在多线程模式下，会创建多个对象
 * 因此对于对象的创建方法需要进行同步处理
 */
public class Singleton {

    //饿汉式单例模式，在类初始化的时候已创建实例，不存在多线程问题
    private static Singleton instance1 = new Singleton();

    //懒汉式单例模式
    private static Singleton instance = null;

    //私有化
    private Singleton() {

    }

    /**
     * 同步处理
     * @return
     */
    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class){
                instance = new Singleton();
            }
            return instance;
        } else {
            return instance;
        }
    }



    public void print(){
        System.out.print("单例模式不能传入参数");
    }
}
