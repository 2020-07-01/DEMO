package thread.testcache;
/**
 * 使用HashMap和同步机制来初始化缓存
 * @author qiang
 *
 * @param <A>
 * @param <V>
 */
public interface Computable<A,V> {

	V compute(A arg) throws InterruptedException  ;
}
