package thread;

import lombok.SneakyThrows;

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





    public static void main(String[] args) {

        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.handle();

    }
}
