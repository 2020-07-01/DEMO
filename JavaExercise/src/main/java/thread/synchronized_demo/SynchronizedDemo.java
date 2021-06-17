package thread.synchronized_demo;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import lombok.SneakyThrows;

/**
 * @ClassName : SynchronizedDemo
 * @Author : yq
 * @Date: 2021-05-23
 * @Description :
 */
public class SynchronizedDemo {

    /**
     * 对象锁
     * 锁住的是对象
     *
     * @throws InterruptedException
     */
    public synchronized void test1() throws InterruptedException {
        System.out.println("获取锁......");
        Thread.sleep(3000);
        System.out.println("释放锁......");
    }

    /**
     * 对象锁
     * 锁住的是当前对象
     *
     * @throws InterruptedException
     */
    public void test2() throws InterruptedException {
        synchronized (this) {
            System.out.println("获取锁......");
            Thread.sleep(3000);
            System.out.println("释放锁......");
        }
    }


    /**
     * 对象锁
     * 锁住的是demo对象
     *
     * @param demo
     * @throws InterruptedException
     */
    public void test3(SynchronizedDemo demo) throws InterruptedException {
        synchronized (demo) {
            System.out.println("获取锁......");
            Thread.sleep(3000);
            System.out.println("释放锁......");
        }
    }

    /**
     * 类锁
     * 锁住的是SynchronizedDemo 的class 对象
     * 因为一个类也有一个class 对象
     *
     * @throws InterruptedException
     */
    public void test4() throws InterruptedException {
        synchronized (SynchronizedDemo.class) {
            System.out.println("获取锁......");
            Thread.sleep(3000);
            System.out.println("释放锁......");
        }
    }

    /**
     * 类锁
     * 锁住的是当前类的class 对象
     */
    public static synchronized void test5() throws InterruptedException {
        System.out.println("获取锁......");
        Thread.sleep(3000);
        System.out.println("释放锁......");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                SynchronizedDemo main = new SynchronizedDemo();
                try {
                    main.test5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }


}
