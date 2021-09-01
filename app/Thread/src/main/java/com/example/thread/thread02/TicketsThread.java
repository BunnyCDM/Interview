package com.example.thread.thread02;

/**
 * Created by mac on 2019/7/21.
 */
public class TicketsThread {

    public static void main(String[] args) {
        //创建三个线程，模拟三个窗口卖票
        MyThread mt1 = new MyThread("窗口1");
        MyThread mt2 = new MyThread("窗口2");
        MyThread mt3 = new MyThread("窗口3");

        mt1.start();
        mt2.start();
        mt3.start();
    }

}


class MyThread extends Thread {

    private int ticketsCount = 5;//一共有5张火车票
    private String name;//窗口，也就是线程的名字

    public MyThread(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        super.run();

        while (ticketsCount > 0) {
            ticketsCount--;//如果还有票，就卖掉一张
            System.out.println(name + "卖了1张票，剩余票数为：" + ticketsCount);

        }
    }
}
