package thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试readLock()方法：此方法可以进行读操作并发读取
 * 
 * @author qiang
 *
 */
public class TestReentrantReadWriteLock {
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void get(Thread thread) {
		lock.readLock().lock();
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 20) {
				System.out.println(thread.getName() + "正在进行读操作");
			}
			System.out.println(thread.getName() + "读操作完毕");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.readLock().unlock();// 释放锁
		}

	}

//	public static void main(String[] args) {
//		TestReentrantReadWriteLock test = new TestReentrantReadWriteLock();
//
//		new Thread() {
//			public void run() {
//				test.get(Thread.currentThread());
//			};
//		}.start();
//
//		new Thread() {
//			public void run() {
//				test.get(Thread.currentThread());
//			};
//		}.start();
//	}

}
