package com.example.designpatterns.shejimoshixingwei.StatePattern;

public class Test {
	public static void main(String[] args) {
		// 新开一个银行账户
		Context account = new Context("Andy.Chen");

		// 存取款等系列操作
		account.deposit(500.0);
		account.deposit(300.0);
		account.deposit(550.0);
		account.payInterest();
		account.withdraw(2000.00);
		account.withdraw(1100.00);

	}
}
