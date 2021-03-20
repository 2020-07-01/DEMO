package thread.excel;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName : CreateExcel
 * @Author : yq
 * @Date: 2021-03-13
 * @Description :
 */
public class CreateExcel {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countdownLatch = new CountDownLatch(2);
        System.out.println("主线程开始执行。。。。。。");

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(10000);
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                countdownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                Thread.sleep(3000);
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                countdownLatch.countDown();
            }
        }).start();

        System.out.println("等待两个子线程执行完毕。。。。。。");
        countdownLatch.await();
        System.out.println("两个子线程执行完毕，主线程继续执行......");

    }
}
