package com.example.thread.thread01;

/**
 * Created by mac on 2019/7/21.
 */
public class KeyPersonThread extends Thread {


    @Override
    public void run() {
        super.run();

        System.out.println(Thread.currentThread().getName() + "开始了战斗");

        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "左突右杀，攻击隋军");
        }

        System.out.println(Thread.currentThread().getName() + "结束了战斗");
    }


}
