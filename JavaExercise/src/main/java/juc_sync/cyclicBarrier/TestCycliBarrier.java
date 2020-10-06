package juc_sync.cyclicBarrier;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: qiang
 * @Description: 模拟所有的游戏玩家准备好之后进入游戏
 * @other:
 * @Date: 2019/9/6 14:00
 */
public class TestCycliBarrier extends Thread {

	CyclicBarrier cyclicBarrier;

	TestCycliBarrier(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {

		try {
			sleep(1000);
			System.out.println("等待的线程的数量：" + cyclicBarrier.getNumberWaiting());
			System.out.println("玩家 " + Thread.currentThread().getName() + "准备完毕，等待其他玩家准备");
			cyclicBarrier.await();// 使当前线程被阻塞，直到所有的线程被打破
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {

		ExecutorService e = Executors.newFixedThreadPool(5);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {

			@Override
			public void run() {
				System.out.println("加载完毕，进入游戏");
			}
		});
		for (int i = 1; i <= 5; i++) {
			e.execute(new TestCycliBarrier(cyclicBarrier));
		}
		e.shutdown();
	}
}
