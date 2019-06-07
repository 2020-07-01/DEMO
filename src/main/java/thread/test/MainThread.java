package thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import thread.cancel_close.Volatile_Cancle;

/**
 * @ClassName:  MainThread   
 * @Description:测试条件锁
 * @author: caiji
 * @date: 2019年6月6日 下午8:32:56
 */
public class MainThread {
	static int i = 0;

	public static void sum() {
		i++;
	}

	public static void main(String[] args) {

		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();

		Thread thread1 = new Thread() {

			public void run() {
				lock.lock();
				try {
					System.out.println("线程等待");
					condition.await();
					System.out.println(i);
					System.out.println("线程激活");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					//释放锁
					lock.unlock();
				}

			}
		};

		Thread thread2 = new Thread() {

			public void run() {

				try {
					sum();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		};

		thread2.start();
		thread1.start();

		try {
			//此处让上面的线程线获取锁
			Thread.sleep(2000);
			lock.lock();
			condition.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

}
