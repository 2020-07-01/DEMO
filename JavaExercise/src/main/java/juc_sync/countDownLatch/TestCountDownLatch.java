package juc_sync.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: qiang
 * @Description: 线程1执行1到100的相加，线程2执行100到200的相加，线程3将线程1与线程2的执行结果相加
 * @other:
 * @Date: 2019/9/6 9:56
 */
public class TestCountDownLatch {

	private static int thread1Result, thread2Result;//线程1和线程2的结果

	//创建相加方法
	private static int count(int start, int end) {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum = sum + i;
		}
		return sum;
	}

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		//创建线程1，执行1到100的相加
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				try {
					sleep(2000);
					thread1Result = count(1, 100);
					System.out.println("线程1的结果：" + thread1Result);
				} catch (Exception e) {

				} finally {
					countDownLatch.countDown();//锁存器的数量减1
				}
			}
		};

		//创建线程2，执行100到200的详解
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				try {
					sleep(4000);
					thread2Result = count(100, 200);
					System.out.println("线程2的结果：" + thread2Result);

				} catch (Exception e) {

				} finally {
					countDownLatch.countDown();//锁存器的数量减1
				}
			}
		};

		//创建线程3，进行线程1和线程2结果的相加
		Thread thread3 = new Thread() {
			@Override
			public void run() {
				try {
					countDownLatch.await();//阻塞此线程，直到
					System.out.println("线程1与线程2相加后的结果" + (thread1Result + thread2Result));
				} catch (Exception e) {
				}
			}
		};
		//开启线程
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
