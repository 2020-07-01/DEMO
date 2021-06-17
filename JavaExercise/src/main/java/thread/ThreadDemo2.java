package thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName : ThreadDemo2
 * @Author : yq
 * @Date: 2021-05-30
 * @Description : 线程间通信方式总结
 */
public class ThreadDemo2 {

    /**
     * 1.volatile 修饰共享变量
     * 2.等待唤醒机制
     * 3.LockSupport 等待唤醒机制
     */

    /**
     * 案例：线程A B 两个线程
     * A线程向一个集合里面依次添加元素"abc"字符串，一共添加十次，
     * 当添加到第五次的时候，希望B线程能够收到A线程的通知，
     * 然后B线程执行相关的业务操作
     */

    private static volatile boolean notice = false;

    public void test1() {
        List<String> list = new LinkedList<>();

        //B线程
        new Thread(() -> {
            while (true) {
                if (notice) {
                    System.out.println("B线程开始执行业务逻辑......");
                }
            }
        }).start();

        //A线程
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                list.add("abc");
                if (i == 5) {
                    System.out.println("A线程发送通知......");
                    notice = true;
                }
            }
        }).start();
    }


    List<String> list = new ArrayList<>();

    public void test2() {

        Object lock = new Object();

        //B线程
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (list.size() != 5) {
                        try {
                            //使获取到lock对象锁的线程等待，直到唤醒
                            //此时释放锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

        //A线程
        new Thread(() -> {
            //B线程进入等待状态，此时A线程获取到锁
            synchronized (lock) {
                for (int i = 1; i < 10; i++) {
                    list.add("abc");
                    if (i == 5) {
                        //唤醒lock对象上等待的线程
                        lock.notify();
                    }
                }
            }
        }).start();
    }


    public void test3() {

        //线程B
        final Thread threadB = new Thread(() -> {
            while (true) {
                if (list.size() != 5) {
                    //无限期暂停当前线程
                    //此方法直接暂停当前线程，不考虑是否获取锁
                    LockSupport.park();
                }
            }
        });
        threadB.start();


        //线程A
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                list.add("abc");
                if (i == 5) {
                    //唤醒线程B
                    //此方法可指定线程进行唤醒
                    LockSupport.unpark(threadB);
                }
            }
        }).start();
    }
}
