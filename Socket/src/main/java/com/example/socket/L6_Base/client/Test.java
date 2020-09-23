package com.example.socket.L6_Base.client;

import com.example.socket.L4.Tools;

import java.util.Arrays;

/**
 * Created by mac on 2020-09-22.
 */
public class Test {

    public static void main(String[] args) {

        test4();
    }

    public static final int OP_READ = 1 << 0;
    public static final int OP_WRITE = 1 << 2;
    public static final int OP_CONNECT = 1 << 3;
    public static final int OP_ACCEPT = 1 << 4;

    private static void test4() {
        System.out.println("OP_READ is " + OP_READ);
        System.out.println("OP_WRITE is " + OP_WRITE);
        System.out.println("OP_CONNECT is " + OP_CONNECT);
        System.out.println("OP_ACCEPT is " + OP_ACCEPT);


        // key.interestOps(key.readyOps() & ~keyOps);


        int i1 = OP_READ & ~OP_WRITE;
        System.out.println("i1 is " + i1);

        int i2 = OP_READ & OP_WRITE;
        System.out.println("i2 is " + i2);

        int i3 = ~OP_WRITE;
        System.out.println("i3 is " + i3);

        int i4 = OP_READ | OP_WRITE;
        System.out.println("i4 is " + i4);
    }

    private static void test3() {

        byte[] bytes = new byte[]{0, 0, 0, 0, 0, 0, 32, 7, 0, 0, 0, 126, 8, 0, 0, 81};
        byte[] bytes_no = new byte[]{0, 0, 32, 7};

        int i = Tools.byteArrayToInt(bytes_no);
        System.out.println(i);
        System.out.println("16:" + Integer.toString(i, 16));

        byte[] bytes_no_ = Tools.intToByteArray(i);
        System.out.println(Arrays.toString(bytes_no_));

    }

    /**
     * 验证continue的使用
     */
    private static void test1() {
        int count = 1;

        do {
            System.out.println("入口1 " + count);

            count = ++count;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count > 10 && count < 20) {
                System.out.println("入口======");
                continue;//不跳出循环，不执行下面的操作
            }

            System.out.println("入口2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (true);

    }

    /**
     * 验证break的使用
     */
    private static void test2() {
        int count = 1;

        do {
            System.out.println("入口1 " + count);

            count = ++count;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count > 10 && count < 20) {
                System.out.println("入口======");
                break;//直接跳出循环
            }

            System.out.println("入口2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (true);

    }
}
