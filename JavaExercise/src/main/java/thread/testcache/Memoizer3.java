package thread.testcache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javax.security.auth.callback.LanguageCallback;

public class Memoizer3<A,V> implements Computable<A, V> {

	private final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
	private   Computable<A,V> c;
	
	public V compute(final A arg) throws InterruptedException  {
		Future<V> f =    cache.get(arg);
		if(f == null)
		{
			Callable<V> eval = new  Callable<V>() {
				public V call() throws InterruptedException {
					return  c.compute(arg);
				}
			};
			FutureTask<V> futureTask = new FutureTask<V>(eval);
			f = futureTask;
			cache.put(arg, futureTask);
			futureTask.run();
		}
		 
		try {
			return f.get();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}
}
