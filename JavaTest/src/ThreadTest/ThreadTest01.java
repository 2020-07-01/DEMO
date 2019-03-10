package ThreadTest;
/**
 * 	测试:多线程中wait()方法与sleep()方法
 * @author qiang
 *
 */
public class ThreadTest01 implements Runnable {
	 
	int number = 10; 
 
	
	public void addHundred() throws Exception {
		System.out.println("addHundred  begin"); 
		//this指的是当前对象，threadTest01
		System.out.println(this);
		/*
		 * synchronized的两种用法：
		 * 一是用在代码块上面：如下
		 * 二是用在方法上面
		 */
		//此代码块为共享资源，输出110
		synchronized (this) {
			 number += 100; 
			 System.out.println("addHundred:" + number); 
			 } 
		 System.out.println("addHundred  end");
		 }
	
	//
	public void wait2Seconds() throws Exception {
		System.out.println("wait2Seconds begin "); 
		synchronized (this){ 
		/**
        * (休息2S,阻塞线程)
        * *以验证当前线程对象的机锁被占用时,
          * *是否被可以访问其他同步代码块
          */ 
			System.out.println(".............wait begin..................");
			this.wait(2000);
			number *= 200; 
			System.out.println(".............wait end.................."); 
          } 
	 System.out.println("wait2Seconds end "); 
	 } 
	
	 public void sleep2Seconds() throws Exception { 
		 System.out.println("sleep2Seconds begin "); 
		 synchronized (this) {
			 /**
			  * (休息2秒阻塞线程)
			  * 以验证当前线程的机锁被占用时
			  * 是否可以访问其他同步代码块
			  */
		
			 System.out.println("............sleep begin..................."); 
			 //让线程暂停执行一段时间，时间一到继续执行，在睡眠过程中，任然有对象锁
			 Thread.sleep(2000);
			 number *= 200;
			 System.out.println(".............sleep end.................."); 
			 } 
		 System.out.println("sleep2Seconds end ");
	} 
	 
	 @Override 
	 //run方法执行线程
	 public void run() {
		 try { 
			 addHundred();
			 } catch (Exception e) { 
				 e.printStackTrace(); } 
			 } 
	 
	 
	 public static void main(String[] args) throws Exception {
				 ThreadTest01 threadTest01 = new ThreadTest01();
				 //创建线程
				 Thread thread = new Thread(threadTest01); 
				 //使线程进入就绪状态
				 thread.start(); 

				 //threadTest01.sleep2Seconds(); 
				 threadTest01.wait2Seconds();
				 }
			 
}
