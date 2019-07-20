package com.example.designpatterns.shejimoshixingwei.StatePattern;

/**
 * 用户账户状态:RedState
 *
 * @author Andy ConcreteState类，具体状态，每一个子类实现一个与 Context的一个状态相关的行为。
 */

public class RedState extends State {

    private double serviceFee;

    public RedState(State state) {
        this.balance = state.getBalance();
        this.context = state.getContext();
        initialize();
    }

    /**
     * 模拟初始化基础数据
     */
    private void initialize() {
        interest = 0.0;
        lowerLimit = -100.0;
        upperLimit = 0.0;
        serviceFee = 15.00;
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
        if (balance > upperLimit) {
            context.setState(new SilverState(this));
        }
    }

    @Override
    public void withdraw(double amout) {
        amout = amout - serviceFee;
    }

    @Override
    public void payInterest() {
    }

}
