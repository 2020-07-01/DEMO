package thread.money;

/**
 * @Author: qiang
 * @Description: 柜台取款
 * @other:
 * @Date: 2019/9/4 15:04
 */
public class PersonB extends Thread{
	Bank bank;
	String mode;

	//构造方法
	PersonB(String mode,Bank bank){
		this.mode = mode;
		this.bank = bank;
	}

	@Override
	public void run() {
		while (Bank.money >= 200) {
			try {
				bank.outMoney("counter", 200);
			} catch (Exception e) {
				e.toString();
			}

			try {
				sleep(1000);
			} catch (Exception e) {
				e.toString();
			}

		}
	}
}
