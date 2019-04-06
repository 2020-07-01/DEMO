package thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock()方法测试：获取锁，如果锁被其他线程获取则进行等待
 * @author qiang
 *
 */
public class TestLock {
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	Lock lock = new ReentrantLock();//将lock声明为类的属性
	public void insert(Thread thread) {
		 
		
		lock.lock();// 获取一个锁
		try {
			System.out.println(thread.getName() + "得到了锁");
			for (int i = 0; i < 5; i++) {
				arrayList.add(i);
			}
		} catch (Exception e) {

			// TODO: handle exception
		} finally {
			System.out.println(thread.getName() + "得到了释放");
			lock.unlock();// 释放锁
		}

	}

	public static void main(String[] args) {
		final TestLock test = new TestLock();
		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();
		
		
		int s = 0;
		for(int i = 0;i<=50;i++)
		{
			s =i+ s;
		}
		System.out.println(s);
	}

}
