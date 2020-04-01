package thread.runable_callbale;

public class TestRunnbale implements Runnable {

	private final String arg;

	// 构造方法
	public TestRunnbale(String arg) {
		this.arg = arg;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1);// 线程阻塞1秒。此时的异常只能内部消化，无法抛出
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("hello  " + this.arg);

	}

	public static void main(String[] args) {
		TestRunnbale testRunnbale = new TestRunnbale("world");

		// 作为Thread的参数
		Thread thread = new Thread(testRunnbale);
		thread.start();

	}

}
