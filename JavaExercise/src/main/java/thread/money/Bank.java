package thread.money;

import java.util.Objects;

/**
 * @Author: qiang
 * @Description: 两个人在柜台和ATM机同时取同一个账户的钱
 * @other:
 * @Date: 2019/9/4 14:43
 */
public class Bank {

	//创建静态账户200元
	static int money = 1000;

	//ATM机取钱
	public void ATM(int amount){
		Bank.money = Bank.money - amount;
		System.out.println("ATM机取钱"+ amount + "，还剩"+Bank.money);
	}

	//柜台取钱
	public void counter(int amount){
		Bank.money = Bank.money - amount;
		System.out.println("柜台取钱"+ amount + "，还剩"+Bank.money);
	}

	//创建取款方法,
	public synchronized void outMoney(String mode,int money) throws Exception{

		if(money > Bank.money)
		{
			throw new Exception("余额不足");
		}
		else{
			//ATM机取款
			if(Objects.equals(mode, "ATM"))
			{
				ATM(money);
			}else {//柜台取款
				counter(money);
			}
		}
	}
}
