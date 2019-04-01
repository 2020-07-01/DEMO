package thread.synchronizedClass;

import java.util.concurrent.CountDownLatch;

/**
 * 测试闭锁
 * @author qiang
 *
 */
public class TestCountDownLatch {

	private CountDownLatch countDownLatch;

	private int start = 10;
	private int mid = 100;
	private int end = 200;

	private volatile int tmpRes1, tmpRes2;

	// 实现累加
	private int add(int start, int end) {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum = sum + i;
		}

		return sum;
	}

	// 实现线程1与线程2的结果相加
	public int s(int a, int b) {
		return a + b;
	}

	// 计算
	public void calculate() {
		countDownLatch = new CountDownLatch(2);

		Thread thread1 = new Thread(() -> {
			try {
				// 线程一执行1到100相加
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + ":开始执行");
				tmpRes1 = add(start, mid);
				System.out.println(Thread.currentThread().getName() + ":calculate ans: " + tmpRes1);

			} catch (Exception e) {

			} finally {
				countDownLatch.countDown();// 计数器减一，如果计数器为0时则释放所有的线程
			}
		}, "thread1");

		Thread thread2 = new Thread(() -> {
			try {
				// 线程一执行1到100相加
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + ":开始执行");
				tmpRes2 = add(mid, end);
				System.out.println(Thread.currentThread().getName() + ":calculate ans: " + tmpRes2);

			} catch (Exception e) {

			} finally {
				countDownLatch.countDown();// 计数器减一，如果计数器为0时则释放所有的线程，此时为0
			}
		}, "thread2");

		// 将thread1与thread2的结果相加
		Thread thread3 = new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + "：开始执行");
				// 阻塞线程，直到计数器为0时唤醒线程
				countDownLatch.await();// 注意为await

				int ans = s(tmpRes1, tmpRes2);
				System.out.println(Thread.currentThread().getName() + "calculate ans:" + ans);

			} catch (Exception e) {

			}
		}, "thread3");

		thread3.start();

		thread1.start();
		thread2.start();

	}

	public static void main(String[] args) throws InterruptedException {
		TestCountDownLatch demo = new TestCountDownLatch();
		demo.calculate();

		 Thread.sleep(1000);
	}

}
