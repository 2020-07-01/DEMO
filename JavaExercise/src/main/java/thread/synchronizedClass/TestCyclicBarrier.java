package thread.synchronizedClass;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestCyclicBarrier implements Runnable {

	private final String name;
	private final CyclicBarrier cyclicBarrier;

	public TestCyclicBarrier(String string, CyclicBarrier cyclicBarrier) {
		this.name = string;
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));// 0和3之间分布的整数
			System.out.println(name + "已经准备，等待其他玩家......");
			cyclicBarrier.await();// 使当前线程被阻塞，直到所有的线程被打破
			TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));// 0和3之间分布的整数
			System.out.println(name + "已加入游戏");

		} catch (InterruptedException e) {
			System.out.println(name + "已经离开游戏");
		} catch (BrokenBarrierException e) {
			System.out.println(name + "已经离开游戏");
		}

	}

	public static void main(String[] args) {
		ExecutorService e = Executors.newFixedThreadPool(5);
		final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			public void run() {
				System.out.println("当所有的线程到达时，优先执行此线程:所有线程都已经到栅栏点");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

		});// 等待线程的数量为5
		for (int i = 0; i < 5; i++) {
			e.execute(new TestCyclicBarrier("玩家" + i, barrier));
		}
		e.shutdown();
	}
}
