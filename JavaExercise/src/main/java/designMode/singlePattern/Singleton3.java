package designMode.singlePattern;

/**
 * @ClassName : Singleton3
 * @Author : yq
 * @Date: 2020-08-30
 * @Description : 静态内部类创建单例模式
 */
public class Singleton3 {

    /**Observer
     * 1、反射可破坏
     */
    private Singleton3() {

    }

    public Singleton3 getInstance() {
        return InnerClass.singleton3;
    }

    public static class InnerClass {

        public static Singleton3 singleton3 = new Singleton3();

    }


}
