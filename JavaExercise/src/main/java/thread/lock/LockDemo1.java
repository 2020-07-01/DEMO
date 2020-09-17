package thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : LockDemo1
 * @Author : yq
 * @Date: 2020-09-17
 * @Description : 乐观锁 CAS
 */
public class LockDemo1 {

    static int count = 0;

    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void increase() {
        atomicInteger.incrementAndGet();
        //count++;
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[20];

        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 2) {
            //当前线程等待
            Thread.yield();
        }
        System.out.println(atomicInteger.get());
    }
}
