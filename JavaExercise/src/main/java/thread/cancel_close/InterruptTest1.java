package thread.cancel_close;

/**
 * 线程非阻塞状态下的中断测试
 * 
 * @author qiang
 *
 */
public class InterruptTest1 implements Runnable {

	public void run() {
		int i = 0;
		System.out.println("中断前的状态：" + Thread.currentThread().isInterrupted());
		while (!Thread.currentThread().isInterrupted()) {
			i = i + 1;
		}
		System.out.println("i 值为：" + i);
		System.out.println("中断后的状态：" + Thread.currentThread().isInterrupted());

		/*
		 * 此时处于非阻塞状态下发生中断 此状态下如果不触发InterruptException，中断状态将一直保持
		 */
		try {

		} catch (Exception e) {
			// 下面代码不会被运行
			Thread curr = Thread.currentThread();
			System.out.println("Thread线程的中断状态：" + Thread.currentThread().isInterrupted());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		InterruptTest1 interruptTest = new InterruptTest1();
		Thread thread = new Thread(interruptTest);

		thread.start();// 开始运行thread线程
		Thread.sleep(100);// main线程进入睡眠状态，此时正在进行计算
		thread.interrupt();
		// 此时保持中断的状态，输出为true
		System.out.println("中断后状态一直保持：" + thread.isInterrupted());
	}
}
