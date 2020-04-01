package thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockinterruptibly()：当通过这个方法获取锁时，如果线程正在等待获取，则这个线程可以响应中断，即中断线程的等待过程
 * 
 * @author qiang
 *
 */
public class TestLockInterruptibly {
	private Lock lock = new ReentrantLock();
	ArrayList<Integer> list = new ArrayList<Integer>();

	public void insert(Thread thread) throws InterruptedException {
		lock.lockInterruptibly();// 获取中断锁
		try {
			System.out.println(thread.getName() + "得到了锁");
			long startTime = System.currentTimeMillis();
			for (int i = 0;; i++) {
				if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
					break;
				list.add(i);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println(thread.getName() + "执行finally");
			System.out.println(thread.getName() + "释放了锁");
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		TestLockInterruptibly test = new TestLockInterruptibly();
		MyThread thread1 = new MyThread(test);
		MyThread thread2 = new MyThread(test);

		thread1.start();

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		thread2.start();

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// 在线程thread2无法获取锁而在等待的过程中，是可以响应中断的
		thread2.interrupt();
	}

}

class MyThread extends Thread {
	TestLockInterruptibly test = new TestLockInterruptibly();

	public MyThread(TestLockInterruptibly test) {
		this.test = test;
	}

	public void run() {
		try {
			test.insert(Thread.currentThread());
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName() + "被中断");
		}

	}
}
