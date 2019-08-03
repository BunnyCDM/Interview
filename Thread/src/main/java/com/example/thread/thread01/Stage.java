package com.example.thread.thread01;

/**
 * Created by mac on 2019/7/21.
 * <p>
 * 隋唐演义大戏舞台
 */
public class Stage extends Thread {


    @Override
    public void run() {
        super.run();

        System.out.println("观看隋唐演义");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("大幕拉开");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("话说隋唐末年，随军与农民起义军杀的昏天黑地");

        ArmyRunnable armyTaskOfSuiDnasty = new ArmyRunnable();
        ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();

        //使用Runnable接口创建线程
        Thread armyOfSuiDnasty = new Thread(armyTaskOfSuiDnasty, "隋军");
        Thread armyOfRevolt = new Thread(armyTaskOfRevolt, "农民起义军");

        armyOfSuiDnasty.start();
        armyOfRevolt.start();

        try {
            //舞台线程休眠，大家专心观看军队厮杀
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("正当双方激战正酣，半路杀出了个程咬金");
        Thread mrCheng = new KeyPersonThread();
        mrCheng.setName("程咬金");
        System.out.println("程咬金的理想就是结束战争，使百姓安居乐业");

        //停止军对作战，停止线程的该方法
        armyTaskOfSuiDnasty.keepRunning = false;
        armyTaskOfRevolt.keepRunning = false;
        //不能使用stop李停止线程，这样会造成程序突然终止造成数据业务有害！！！
        //armyOfSuiDnasty.stop();
        //armyOfRevolt.stop();
        //可以查看java API文档，并不能终止线程
        //armyOfSuiDnasty.interrupt();
        //armyOfRevolt.interrupt();

        try {
            //休眠一会，让停火协议得到执行
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //历史大戏留给关键先生
        mrCheng.start();

        try {
            //万众属目，所有线程等待程先生完成历史使命
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("战争结束，人民安居乐业，程先生实现了人生梦想，为人民做出了贡献");
        System.out.println("谢谢观看隋唐演义，再见");

    }

    public static void main(String[] args) {
        Stage stage = new Stage();
        stage.start();
    }

}
