package thread.testcache;

import java.util.HashMap;
import java.util.Map;

public class Memoizer1<A,V> implements Computable<A,V>{

	private final Map<A,V> cache = new HashMap<A,V>();
	private final Computable<A,V> c;
	
	public Memoizer1(Computable<A,V> c) {
		this.c = c;
	}
	
	 
	//使用同步方法防止两个不同的线程同时访问
	public synchronized  V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if(result == null)//检查结果是否已经在缓存当中
		{
			result = c.compute(arg);//进行计算
			cache.put(arg, result);//将计算结果缓存在hashmap中
		}
		return result;
	}
	
	 
	
			

}
