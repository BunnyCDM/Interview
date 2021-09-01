package com.example.asynchronized;

/**
 * Created by mac on 2019/1/18.
 */

public class 修饰一个静态的方法 {

    public synchronized static void method() {

    }

    static class SyncThread implements Runnable {

        private static int count;

        public SyncThread() {
            count = 0;
        }

        @Override
        public void run() {
            method();
        }

        public static synchronized void method() {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) {
        SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();
        /**
         SyncThread1:0
         SyncThread1:1
         SyncThread1:2
         SyncThread1:3
         SyncThread1:4
         SyncThread2:5
         SyncThread2:6
         SyncThread2:7
         SyncThread2:8
         SyncThread2:9
         */
    }
}
