package com.example.designpatterns.shejimoshixingwei.StatePattern;

/**
 * 用户账户状态:SilverState
 *
 * @author Andy ConcreteState类，具体状态，每一个子类实现一个与 Context的一个状态相关的行为。
 */

public class SilverState extends State {

    public SilverState(State redState) {
        this(redState.balance, redState.context);
    }

    public SilverState(double balance, Context context) {
        this.balance = balance;
        this.context = context;
        inititlize();
    }

    /**
     * 模拟初始化基础数据
     */
    private void inititlize() {
        interest = 0.0;
        lowerLimit = 0.0;
        upperLimit = 1000.0;
    }

    @Override
    public void deposit(double amout) {
        System.out.println("deposit");
        balance += amout;
        stateChangeCheck();
    }

    /**
     * 状态检测
     */
    private void stateChangeCheck() {
        System.out.println("stateChangeCheck 1");
        if (balance < lowerLimit) {
            System.out.println("stateChangeCheck 2");
            context.setState(new RedState(this));
        } else if (balance > upperLimit) {
            System.out.println("stateChangeCheck 3");
            context.setState(new GoldState(this));
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
