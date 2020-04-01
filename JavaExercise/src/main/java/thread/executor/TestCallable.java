package thread.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试Callable Callaable有返回值，使用Future来接受返回的结果
 * 
 * @author qiang
 *
 */
public class TestCallable implements Callable<String> {

	private int id;

	public TestCallable(int id) {
		this.id = id;
	}

	public String call() throws Exception {
		System.out.println("call()方法被自动调用" + Thread.currentThread().getName());

		// 将返回结果被future的get方法得到
		return "call()方法被调用，任务返回的结果是：" + id + "   " + Thread.currentThread().getName();
	}

	// 测试
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		List<Future<String>> resultList = new ArrayList<Future<String>>();

		// 创建10个任务并执行
		for (int i = 0; i < 10; i++) {
			// 使用ExecutorService执行Callable类型的任务，并将结果保留到future变量中
			Future<String> future = executorService.submit(new TestCallable(i));
			// 将结果保留到List中
			resultList.add(future);
		}
		// 遍历结果的任务
		for (Future<String> fs : resultList) {
			while (!fs.isDone()) {// 如果此任务完成，返回true
				try {
					System.out.println(fs.get());// 打印各个线程执行的结果
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					executorService.shutdown();
				}
			}
		}
	}
}
