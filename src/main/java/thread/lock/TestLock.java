package thread.lock;

import java.awt.Frame;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class TestLock implements Runnable {
	
	private Lock lock = new ReentrantLock();

	int a = 0;
	public void run() {
		//上锁
		lock.lock();
		for (int i = 0; i < 1000; i++) {
			System.out.println("当前线程名： " + Thread.currentThread().getName() + " ,i = " + i);
		}
		//释放锁
		lock.unlock();
	}
	

	public static void main(String[] args) {
		TestLock myReenrantLock = new TestLock();
		
		//创建三个线程
		Thread thread1 = new Thread(myReenrantLock);
		Thread thread2 = new Thread(myReenrantLock);
		Thread thread3 = new Thread(myReenrantLock);
		Thread thread4 = new Thread(myReenrantLock);
		thread1.start();
		thread2.start();
		//thread3.start();
		//thread4.start();
	}
}
