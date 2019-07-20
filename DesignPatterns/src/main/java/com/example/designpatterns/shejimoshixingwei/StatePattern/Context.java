package com.example.designpatterns.shejimoshixingwei.StatePattern;

/**
 * Context类，维护一个ConcreteState子类的实例 这个实例定义当前的状态。
 *
 * @author Andy
 */

public class Context {

    private State state;
    private String owner;

    public Context(String owner) {
        // 新开账户默认为 Silver状态
        this.owner = owner;
        this.state = new SilverState(0.0, this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void deposit(double amount) {
        state.deposit(amount);
        System.out.println(owner + " Deposited  " + amount);
        System.out.println(owner + " Balance =  " + state.getBalance());
        System.out.println("Status = " + state.getClass().getSimpleName());
        System.out.println("==============================================");
    }

    public void withdraw(double amount) {
        state.withdraw(amount);
        System.out.println(owner + " Withdrew  " + amount);
        System.out.println(owner + " Balance =  " + state.getBalance());
        System.out.println("Status = " + state.getClass().getSimpleName());
        System.out.println("==============================================");
    }

    public void payInterest() {
        state.payInterest();
        System.out.println(owner + " Interest Paid  ");
        System.out.println(owner + " Balance =  " + state.getBalance());
        System.out.println("Status = " + state.getClass().getSimpleName());
        System.out.println("==============================================");
    }

}
