package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition_Lock {

	public static void main(String[] args) {
		
		//创建非公平重入锁
		ReentrantLock lock = new ReentrantLock();
		//获取一个条件锁
		Condition condition = lock.newCondition();
	 
		
		
	}
}
