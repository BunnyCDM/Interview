package com.example.asynchronized;

/**
 * Created by mac on 2019/1/18.
 */

public class 修饰一个代码块 {


    static class SyncThread implements Runnable {

        private static int count;

        public SyncThread() {
            count = 0;
        }

        @Override
        public void run() {
            synchronized (this) {
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

        public int getCount() {
            return count;
        }

    }

    static class Counter implements Runnable {

        private int count;

        public Counter() {
            count = 0;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            if (threadName.equals("A")) {
                countAdd();
            } else if (threadName.equals("B")) {
                printCount();
            }
        }

        public void countAdd() {
            synchronized (this) {
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

        //非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
        public void printCount() {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " count:" + count);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //银行账户类
    static class Account {
        String name;
        float amount;

        public Account(String name, float amount) {
            this.name = name;
            this.amount = amount;
        }

        //存钱
        public void deposit(int amt) {
            amount += amt;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //取钱
        public void withdraw(int amt) {
            amount -= amt;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public float getBalance() {
            return amount;
        }
    }

    //账户操作类
    static class AccountOperator implements Runnable {

        private Account account;

        public AccountOperator(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            synchronized (account) {
                account.deposit(500);
                account.withdraw(500);
                System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
            }
        }
    }

    public static void main(String args[]) {

        //测试一
        SyncThread s1 = new SyncThread();
        SyncThread s2 = new SyncThread();
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s2);
//        t1.start();
//        t2.start();
        /**
         Thread-1:0
         Thread-0:1
         Thread-0:0
         Thread-1:1
         Thread-0:2
         Thread-1:3
         Thread-0:4
         Thread-1:5
         Thread-0:6
         Thread-1:6
         */

        //测试二
        SyncThread s = new SyncThread();
        Thread t11 = new Thread(s);
        Thread t22 = new Thread(s);
//        t11.start();
//        t22.start();
        /**
         Thread-2:0
         Thread-2:1
         Thread-2:2
         Thread-2:3
         Thread-2:4
         Thread-3:5
         Thread-3:6
         Thread-3:7
         Thread-3:8
         Thread-3:9
         */

        //测试三
        Counter counter = new Counter();
        Thread thread1 = new Thread(counter, "A");
        Thread thread2 = new Thread(counter, "B");
//        thread1.start();
//        thread2.start();
        /**
         A:0
         B count:1
         A:1
         B count:2
         B count:2
         A:2
         B count:3
         A:3
         B count:4
         A:4
         */

        //测试四
        //public static final Object signal = new Object(); // 线程间通信变量
        //将account改为Demo00.signal也能实现线程同步
        Account account = new Account("zhang san", 10000.0f);
        AccountOperator accountOperator = new AccountOperator(account);
        final int THREAD_NUM = 5;
        Thread threads[] = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(accountOperator, "Thread" + i);
            threads[i].start();
        }
        /**
         Thread0:10000.0
         Thread4:10000.0
         Thread3:10000.0
         Thread2:10000.0
         Thread1:10000.0
         */
    }


}
