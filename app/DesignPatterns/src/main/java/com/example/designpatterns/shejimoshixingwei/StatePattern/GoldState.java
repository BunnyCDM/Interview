package com.example.designpatterns.shejimoshixingwei.StatePattern;

/**
 * 用户账户状态:GoldState
 *
 * @author Andy
 *         ConcreteState类，具体状态，每一个子类实现一个与Context的一个状态相关的行为。
 */

public class GoldState extends State {

    public GoldState(State silverState) {
        this(silverState.balance, silverState.context);
    }

    public GoldState(double balance, Context context) {
        this.balance = balance;
        this.context = context;
        initialze();
    }

    /**
     * 模拟初始化基础数据
     */
    private void initialze() {
        interest = 0.05;
        lowerLimit = 1000.0;
        upperLimit = 10000000.0;
    }

    @Override
    public void deposit(double amout) {
        balance += amout;
        stateChangeCheck();
    }

    /**
     * 状态检测
     */
    private void stateChangeCheck() {
        if (balance < 0.0) {
            context.setState(new RedState(this));
        } else if (balance < lowerLimit) {
            context.setState(new SilverState(this));
        }
    }

    @Override
    public void withdraw(double amout) {
        balance -= amout;
        stateChangeCheck();
    }

    @Override
    public void payInterest() {
        balance += interest * balance;
        stateChangeCheck();
    }

}
