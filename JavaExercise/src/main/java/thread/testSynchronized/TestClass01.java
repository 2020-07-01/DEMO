package thread.testSynchronized;

public class TestClass01 implements Runnable {
	private int number;

	TestClass01() {
		number = 0;
	}

	public void m1() {
		synchronized (this) {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (number++));
					Thread.sleep(500);
				} catch (Exception e) {
					System.out.println("异常");
				}
			}
		}
	}

	public void m2() {
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (number++));
				Thread.sleep(500);
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
		TestClass01 test1 = new TestClass01();
		TestClass01 test2 = new TestClass01();
		Thread thread1 = new Thread(test1, "thread1");
		Thread thread2 = new Thread(test2, "thread2");
		thread1.start();
		thread2.start();
	}
}