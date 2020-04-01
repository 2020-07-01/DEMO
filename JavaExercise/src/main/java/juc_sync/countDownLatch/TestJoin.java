package juc_sync.countDownLatch;

/**
 * @Author: qiang
 * @Description: 线程1执行1到100的相加，线程2执行100到200的相加，线程3将线程1与线程2的执行结果相加
 * @other: 线程1和线程2可以同时执行，线程3必须在线程1和线程2执行结果之后再执行
 * @Date: 2019/9/6 10:29
 */
public class TestJoin {

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


		//创建线程thread2
		Thread thread2 = new Thread("线程2") {
			//重写方法
			@Override
			public void run() {
				try {
					sleep(2000);
					thread2Result = count(100, 200);
				} catch (Exception e) {
					e.getMessage();
				}
				System.out.println("线程2的执行结果：" + thread2Result);
			}
		};

		//创建线程thread1
		Thread thread1 = new Thread("线程1") {
			//重写方法
			@Override
			public void run() {
				try {
					sleep(2000);
					thread1Result = count(1, 100);
				} catch (Exception e) {
					e.getMessage();
				}
				System.out.println("线程1的执行结果：" + thread1Result);
			}
		};


		//创建线程3
		Thread thread3 = new Thread("线程3") {

			//重写方法
			@Override
			public void run() {
				try {

					thread1.join();
					thread2.join();

					sleep(3000);
				} catch (Exception e) {
				}
				System.out.println("线程1和线程2执行结束\n线程3的执行结果为：" + (thread2Result + thread1Result));
			}
		};
		thread1.start();
		thread2.start();
		thread3.start();
	}


}
