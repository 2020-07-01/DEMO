package thread.cancel_close;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author qiang
 *  一个仅运行一秒的素数生成器 线程的运行的取消通过cancel方法设置标志
 *         为了使整个过程可靠，标志canceled设置为volatile
 */
public class Volatile_Cancle implements Runnable {

	private final List<BigInteger> primes = new ArrayList<BigInteger>();

	private  volatile boolean canceled;// 取消标志状态。默认为true

	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;// 设置常量为1

		while (!canceled) {// 此时为false
			p = p.nextProbablePrime();// 返回第一个大于可能是质数的BigInteger的整数
			synchronized (this) {
				primes.add(p);
			}
		}
	}

	// 设置取消标志的状态
	public void cancel() {
		canceled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}

	public static void main(String[] args) {
		Volatile_Cancle primeGenerator = new Volatile_Cancle();
		new Thread(primeGenerator).start();// 运行此线程
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			primeGenerator.cancel();// 强制设置取消标志
		}
		System.out.println(primeGenerator.get());
	}

}
