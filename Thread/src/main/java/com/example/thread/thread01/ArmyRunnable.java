package com.example.thread.thread01;

/**
 * Created by mac on 2019/7/21.
 * <p>
 * 军队线程，模拟作战双方的行为
 */
public class ArmyRunnable implements Runnable {


    //volatile保证了线程可以正确的读取其他现线程写入的值，否则会对读取错误，volatile可见性
    volatile boolean keepRunning = true;

    @Override
    public void run() {

        while (keepRunning) {

            //发动三连击
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "进攻对方【" + i + "】");

                //让出了处理器时间，下次该谁进攻还不一定呢
                Thread.yield();
            }

        }

        System.out.println(Thread.currentThread().getName() + "结束了战斗");
    }
}
