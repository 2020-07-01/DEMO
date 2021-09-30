package thread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName : ThreadDemo
 * @Author : yq
 * @Date: 2021-03-14
 * @Description :
 */
public class ThreadDemo {

    /*************************CyclicBarrier 学习************************************/
    private static CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

    private volatile static String demo = "demo";

    public void handle() {
        BlockingQueue queue = new DelayQueue();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 1000, 5000, TimeUnit.SECONDS, queue, threadFactory);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(50);

        for (int i = 0; i < 50; i++) {
            int finalI = i;
            threadPoolExecutor.submit(new CyclicBarrierClass(cyclicBarrier, finalI));
        }

        boolean finishEd = false;
        while (!finishEd) {
            if (cyclicBarrier.getNumberWaiting() == 0) {
                finishEd = true;
            }
        }

        System.out.println(copyOnWriteArrayList);
    }

    static class CyclicBarrierClass implements Runnable {

        private CyclicBarrier cyclicBarrier;

        private int i;

        CyclicBarrierClass(CyclicBarrier cyclicBarrier, int i) {
            this.cyclicBarrier = cyclicBarrier;
            this.i = i;
        }

        @SneakyThrows
        @Override
        public void run() {
            copyOnWriteArrayList.add(i);
            cyclicBarrier.await();
        }
    }





    public static void main(String[] args) throws InterruptedException {

        Object object = new Object();
        List<Integer> list = new ArrayList<>();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (object){
                    if(list.size() == 0){
                        //当前线程等待，并释放当前线程的锁
                        System.out.println("线程1进入等待状态");
                        object.wait();
                        System.out.println("线程1被唤醒");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    System.out.println("线程2增加list数据......");
                        for (int i = 0; i < 10; i++) {
                           list.add(i);
                        }
                        object.notifyAll();
                    System.out.println("线程2增加完毕l，唤醒所有等待线程......");
                }
            }
        }).start();
    }
}
