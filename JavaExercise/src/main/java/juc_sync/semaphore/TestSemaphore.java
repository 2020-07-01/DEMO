package juc_sync.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: qiang
 * @Description: 10个线程抢占3个资源
 * @other:
 * @Date: 2019/9/6 10:59
 */
public class TestSemaphore extends Thread {

	Semaphore semaphore;

	TestSemaphore(String name, Semaphore semaphore) {
		super();
		setName(name);
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();//获取一个许可
			sleep(2000);//休眠2秒
			System.out.println("线程" + Thread.currentThread().getName() + "获取了许可在执行操作");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			semaphore.release();//释放许可
		}
	}

	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(3);

		//创建10个线程
		ExecutorService e = Executors.newFixedThreadPool(10);
		for(int i = 1;i<=10;i++)
		{
			e.execute(new TestSemaphore("线程"+i,semaphore));
		}
	}


}
