package thread.runable_callbale;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 测试Callable
 * 
 * @author qiang
 *
 */
public class TestCallable implements Callable<String> {
	private String arg;

	public TestCallable(String arg) {
		this.arg = arg;
	}

	public String call() throws Exception {
		// 线程阻塞1秒,会返回这个异常
		Thread.sleep(1000);

		return this.arg + "append some chars and return it!";
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Callable<String> callable = new TestCallable("world");
		Future<String> futuretask = new FutureTask<>(callable);
		Long beginTime = System.currentTimeMillis();

		// 创键线程
		new Thread((Runnable) futuretask).start();// Thread只接受Runnable类型的参数
		// get方法会阻塞线程，反之返回结果
		String result = futuretask.get();
		long endTime = System.currentTimeMillis();

		System.out.println("hello " + result);
		System.out.println("cast :" + (endTime - beginTime) / 1000 + "second");

	}

}
