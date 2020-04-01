package thread.testSynchronized;

/**
 * 测试Synchronized
 * 
 * @author qiang
 *
 */
public class TestMethod implements Runnable {
	private static int number;

	TestMethod() {
		number = 0;
	}

	// 修饰非静态的方法
	public static synchronized void m1() {
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (number++));
				Thread.sleep(200);
			} catch (Exception e) {
				System.out.println("异常");
			}
		}
	}

	public static synchronized void m2() {//此时方法属于类
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (number++));
				Thread.sleep(200);
			} catch (Exception e) {
				System.out.println("异常");
			}
		}
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		 
		if (name.equalsIgnoreCase("thread1")) {
			m1();
		} else {
			m2();
		}
	}

	public static void main(String[] args) {
		  TestMethod testMethod = new TestMethod();
	        Thread thread1  = new Thread(testMethod,"thread1");
	        Thread thread2 = new Thread(testMethod,"thread2");
	        thread1.start();
	        thread2.start();
	}
}