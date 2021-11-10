package com.example.thread.thread01;


public class WrongWayStopThread extends Thread {

    public static void main(String[] args) {
        WrongWayStopThread thread = new WrongWayStopThread();
        System.out.println("Start thread");
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Interrupting thread");
        thread.interrupt();

        System.out.println("Stop thread");
    }


    @Override
    public void run() {
        super.run();

        long time = System.currentTimeMillis();
        while (!this.isInterrupted()) { //其实就相当于隋唐演义中的标志volatile boolean keepRunning = true;
            System.out.println("Thread is running,"+(System.currentTimeMillis() - time));
            //相当于Thread.sleep(1000)
            while ((System.currentTimeMillis() - time < 1000)) {

            }

//            try {
//                //这点会报异常，因为在上面调用了thread.interrupt()，倘若在thread.interrupt();前调用Thread.sleep(1000)，它会清空interrupt状态哟！！！
//                //所以终止线程尽可能使用标志volatile boolean keepRunning = true;
//                //thread.interrupt()可以实现就是要注意使用while ((System.currentTimeMillis() - time < 1000)) {}替代Thread.sleep
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }
}
