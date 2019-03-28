package thread.testcache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String,BigInteger>{

	public BigInteger compute(String arg) throws InterruptedException {
		//在经过长时间的
		return new BigInteger(arg);
	}

}
