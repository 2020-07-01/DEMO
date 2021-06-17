package lock;

/**
 * @ClassName : Demo
 * @Author : yq
 * @Date: 2021-05-21
 * @Description :
 */
public class Demo {

    public static void main(String[] args) {

        Demo demo = new Demo();
        demo.test();
        demo.test2();
    }

    public synchronized void test() {
        System.out.println("this is a method");
    }

    public void test2(){
        synchronized (this){
            System.out.println("this is a method");
            System.out.println("this is a method1");
            System.out.println("this is a method2");
        }
    }
}
