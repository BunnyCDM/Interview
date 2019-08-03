package com.example.thread.thread02;

/**
 * Created by mac on 2019/7/21.
 */
public class TicketsRunnable {


    public static void main(String[] args) {

        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable, "窗口1");
        Thread thread2 = new Thread(runnable, "窗口2");
        Thread thread3 = new Thread(runnable, "窗口3");
        thread1.start();
        thread2.start();
        thread3.start();

    }

    static class MyRunnable implements Runnable {

        private int ticketsCount = 5;//一共有5张火车票


        @Override
        public void run() {

            while (ticketsCount > 0) {
                ticketsCount--;//如果还有票，就卖掉一张
                System.out.println(Thread.currentThread().getName() + "卖了1张票，剩余票数为：" + ticketsCount);

            }
        }
    }

}
