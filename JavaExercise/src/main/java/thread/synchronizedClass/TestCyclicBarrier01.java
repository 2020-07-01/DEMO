package thread.synchronizedClass;

import java.text.SimpleDateFormat;
import java.util.Date;import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestCyclicBarrier01 implements Runnable {
	
	private CyclicBarrier cyclicBarrier;
	private int timeout;
	
	public TestCyclicBarrier01(CyclicBarrier cyclicBarrier,int timeout) {
		this.cyclicBarrier = cyclicBarrier;
		this.timeout = timeout;
	}
	
	
	
	public static void print(String str) {
        SimpleDateFormat dfdate = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + dfdate.format(new Date()) + "]"
                + Thread.currentThread().getName() + str);
    }

	
	
	@Override
	public void run() {
		 print("正在running。。。。。。");
		try {
			TimeUnit.MILLISECONDS.sleep(timeout);
			print("到达栅栏处，等待其他线程的到达");
			cyclicBarrier.await();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		print("所有的线程都已经到达，继续执行各自的任务...");
	}
	

	
	public static void main(String[] args) {
		int count = 5;
		ExecutorService es = Executors.newFixedThreadPool(count);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(count,new Runnable() {

			@Override
			public void run() {
				print("所有线程达到栅栏处，可以在此做一些处理......");;
				
				
			}
			
		});
		
		
	}
}
