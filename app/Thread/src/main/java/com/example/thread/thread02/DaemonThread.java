package com.example.thread.thread02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * thread.setDaemon(true)：设置为守护线程
 */
public class DaemonThread {

    public static void main(String args[]) {
        System.out.println("进入主线程" + Thread.currentThread().getName());
        DaemonRunnable daemonRunnable = new DaemonRunnable();
        Thread thread = new Thread(daemonRunnable);
        thread.setDaemon(true);
        thread.start();

        Scanner sc = new Scanner(System.in);
        sc.next();

        System.out.println("退出主线程" + Thread.currentThread().getName());
    }

    private static void writeToFile() throws Exception {
        File fileName = new File("/Users/mac/Desktop/test.txt");
        System.out.println(fileName.exists());
        OutputStream os = new FileOutputStream(fileName, true);
        int count = 0;
        while (count < 9) {
            os.write(("\r\nword" + count).getBytes());
            System.out.println("'进入守护线程'" + Thread.currentThread().getName()
                    + "向文件中写入word" + count++);
            Thread.sleep(1000);
        }
    }


    static class DaemonRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("'进入守护线程'" + Thread.currentThread().getName());

            try {
                writeToFile();
            } catch (Exception e) {

            }

            System.out.println("退出守护线程'" + Thread.currentThread().getName());
        }

    }
}
