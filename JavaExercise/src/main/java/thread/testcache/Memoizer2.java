package thread.testcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memoizer2<A,V> implements Computable<A,V>{

	private final Map<A,V> cache = new ConcurrentHashMap<A,V>();//线程安全的
	private final Computable<A,V> c;
	
	public Memoizer2(Computable<A,V> c) {
		this.c = c;
	}
	
	 
	 
	public V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if(result == null)//检查结果是否已经在缓存当中
		{
			result = c.compute(arg);//进行计算
			cache.put(arg, result);//将计算结果缓存在hashmap中
		}
		return result;
	}
	
	 
	
			

}
