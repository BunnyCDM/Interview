package com.example.designpatterns.shejimoshixingwei.StatePattern;

/**
 * State类，抽象状态类，定义一个接口以封装 与Context的一个特定状态相关的行为。
 *
 * @author Andy
 */


public abstract class State {
    protected Context context;
    protected double balance;

    protected double interest;
    protected double lowerLimit;
    protected double upperLimit;

    public Context getContext() {
        return context;
    }

    public void setContext(Context account) {
        this.context = account;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //存入金额
    public abstract void deposit(double amout);

    //支出金额
    public abstract void withdraw(double amout);

    //利息
    public abstract void payInterest();
}
