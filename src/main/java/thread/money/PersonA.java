package thread.money;

import monry.Bank;

/**
 * @Author: qiang
 * @Description: ATM取钱
 * @other:
 * @Date: 2019/9/4 14:51
 */
public class PersonA extends Thread {

	Bank bank;
	String mode;

	//构造方法
	PersonA(String mode,Bank bank){
		this.mode = mode;
		this.bank = bank;
	}

	@Override
	public void run() {
		while (Bank.money >= 100) {
			try {
				bank.outMoney("ATM", 100);
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
