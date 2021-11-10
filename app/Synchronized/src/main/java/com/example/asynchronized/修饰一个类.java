package com.example.asynchronized;

/**
 * Created by mac on 2019/1/18.
 */

public class 修饰一个类 {

    class ClassName {
        public void method() {
            synchronized (ClassName.class) {

            }
        }
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

        public static void method() {
            synchronized (SyncThread.class){
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
         SyncThread2:1
         SyncThread1:2
         SyncThread2:3
         SyncThread1:4
         SyncThread2:5
         SyncThread2:6
         SyncThread1:7
         SyncThread1:8
         SyncThread2:8
         */
    }

}
