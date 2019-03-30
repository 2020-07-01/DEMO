package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRunnable implements Runnable {

	public void run() {
		System.out.println(Thread.currentThread().getName() + "线程被调用");
	}

	public static void main(String[] args) {
		// 创建可缓存线程池，如果线程长超过需要处理，可灵活回收空闲线程，若无可回收，则新建线程
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++) {
			// executor方法中的参数是Runnable
			// executorService.execute(new TestRunnable());
			executorService.submit(new TestRunnable());
			System.out.println(i);
		}
		// 关闭线程池
		executorService.shutdown();
	}
}
