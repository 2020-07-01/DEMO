package thread.synchronizedClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.xml.crypto.Data;

/**
 * 测试Semaphore 10个线程竞争三个资源
 * 
 * @author qiang
 *
 */
public class TestSemaphore implements Runnable {

	private Semaphore semaphore;// 创建信号量
	private int sleepTime;

	public TestSemaphore(int sleepTime, Semaphore semaphore) {
		this.sleepTime = sleepTime;
		this.semaphore = semaphore;
	}

	public static void print(String str) {
		SimpleDateFormat dfdata = new SimpleDateFormat("HH:mm:ss");
		System.out.println("[" + dfdata.format(new Date()) + "]" + Thread.currentThread().getName() + str);

	}

	@Override
	public void run() {
		try {
			// 获取资源
			semaphore.acquire();// 在获取许可证前，线程一直阻塞
			print("占用一个资源");
			TimeUnit.MILLISECONDS.sleep(sleepTime);
			print("资源使用结束，释放资源");
			// 释放资源
			semaphore.release();// 释放许可证，将其返回给信号量
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		// 线程数目
		int threadCount = 10;
		// 资源数目
		Semaphore semaphore = new Semaphore(3);
		// 创建线程池
		ExecutorService e = Executors.newFixedThreadPool(threadCount);

		// 启动线程
		for (int i = 0; i < threadCount; i++)
			e.execute(new TestSemaphore((i + 1) * 1000, semaphore));

	}

}
