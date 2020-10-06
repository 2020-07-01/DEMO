package thread.money;

/**
 * @Author: qiang
 * @Description: 主方法
 * @other:
 * @Date: 2019/9/4 15:07
 */
public class ThreadMain {
	public static void main(String[] args) {

		Bank bank = new Bank();

		PersonA personA = new PersonA("ATM", bank);
		PersonB personB = new PersonB("counter", bank);

		personA.start();
		personB.start();
	}

}
