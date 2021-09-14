package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : CountDownLatchDemo
 * @Author : yq
 * @Date: 2021-08-29
 * @Description :
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

    }

    private void test1(){

        CountDownLatch countDownLatch = new CountDownLatch(3);

        //获取计数器值
        countDownLatch.getCount();
        //递减计数器
        countDownLatch.countDown();
        try {

            //当前线程等待，直到计数器为0，或者线程被中断
            countDownLatch.await();
            //当前线程等待，直到计数器为0，或者时间到期，或者线程被终端
            countDownLatch.await(60, TimeUnit.SECONDS);
        }catch (Exception e){


        }


    }


}
