package thread.cancel_close;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {
	public static void Runtime(Runnable runnable,int timeout) {
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
				Future<?> future = executorService.submit(runnable);
		
		try {
			 System.out.println(future.get());//等待指定的计算完成时间
			 
		} catch (Exception e) {
			System.out.println("超时抛出异常");
		}finally {
			future.cancel(true);//取消此任务的执行
		}
	}
	
	
	public static void main(String[] args) {
		
		Runtime(new Runnable() {
			@Override
			public void run() {
				int sum=0;
				for(int i = 0;i<100;i++) {
					sum = sum+i;
				}
			}
		}, 600 );
	}

}
