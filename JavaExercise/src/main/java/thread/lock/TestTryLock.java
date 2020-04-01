package thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()方法测试：用来获取锁，如果获取成功则返回true，如果获取失败，则返回false，即无论如何都会有返回值
 * 
 * @author qiang
 *
 */
public class TestTryLock {

	private ArrayList<Integer> list = new ArrayList<Integer>();
	private Lock lock = new ReentrantLock();

	public void insert(Thread thread) {
		if (lock.tryLock())// 如果获取锁成功
		{
			try {
				System.out.println(thread.getName() + "得到了锁");
				for (int i = 0; i < 5; i++) {
					list.add(i);
				}
			} finally {
				System.out.println(thread.getName() + "释放了锁");
				lock.unlock();
				// TODO: handle finally clause
			}
		}
		else
			System.out.println(thread.getName()+"获取锁失败");
	}

	public static void main(String[] args) {
		final TestTryLock test = new TestTryLock();

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();

		/*new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();
*/
		
		
		System.out.println(Integer.MAX_VALUE);
	}
}
