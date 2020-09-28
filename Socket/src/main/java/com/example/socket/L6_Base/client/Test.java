package com.example.socket.L6_Base.client;

import com.example.socket.L4.Tools;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Created by mac on 2020-09-22.
 * <p>
 * ByteBuffer buffer = ByteBuffer.wrap(byteBuffer);
 * buffer.putInt(total);
 */
public class Test {


    public static void main(String[] args) {
        test7();
    }


    private static void test7() {
        //3 = 二进制=00000011，十六进制=3
        System.out.println(Arrays.toString(Tools.intToByteArrayHighToLow(3)));//[0, 0, 0, 3]
        System.out.println(Tools.byteArrayToIntHighToLow(Tools.intToByteArrayHighToLow(3)));//3

        //352 ：二进制=【00000000 00000000 00000001 01100000】，十六进制=160
        byte[] bytes = Tools.intToByteArrayHighToLow(352);
        System.out.println(Arrays.toString(bytes));//[0, 0, 1, 96]
        System.out.println(Tools.byteArrayToIntHighToLow(Tools.intToByteArrayHighToLow(352)));//352

        System.out.println(Tools.byteToHexString(bytes[3]));//60
        System.out.println(Tools.byteArrayToHexString(bytes));//00000160

        System.out.println(Tools.hexStringToByte("6"));//00000160
        System.out.println(Arrays.toString(Tools.hexStringToByteArray("00000160")));//00000160
        //String cmd = "00A40000023F0100";
        //byte[] bytes = new byte[]{0, -92, 0, 0, 2, 63, 1, 0};

        byte[] bytes1 = new byte[]{0, -80, -107, 0};
        System.out.println(Tools.byteArrayToHexString(bytes1));
        //System.out.println(Arrays.toString(hexStringToByteArray("00000000899666660000000000000000000 0350520200 924205009240000")));

        byte[] bytes2 = new byte[]{0, 0, 53, 5};
        System.out.println(Tools.byteArrayToHexString(bytes2));

    }


    private static void test6() {
        int i = Math.min(5, 10);
        System.out.println("i is " + i);

        int i2 = Math.max(5, 10);
        System.out.println("i2 is " + i2);


        double i3 = Math.random();
        System.out.println("i3 is " + i3);//[0～1）


        System.out.println(Math.ceil(i3)); //向上取整
        System.out.println(Math.floor(i3));//向下取整


        /**
         * java保留小数位
         * 注意：当不保留小数位时四舍五入，只有大于0.5结果才为1哈
         */
        DecimalFormat df = new DecimalFormat("#0.0");
        System.out.println(df.format(0.51));
    }

    private static void test5() {
        int count = 1;
        int count1 = count += count;
        System.out.println("count1 is " + count1);

        int count2 = count1++;
        System.out.println("count2 is " + count2);

        int count3 = ++count2;
        System.out.println("count3 is " + count3);

    }


    private static void test4() {
        int OP_READ = 1 << 0;
        int OP_WRITE = 1 << 2;
        int OP_CONNECT = 1 << 3;
        int OP_ACCEPT = 1 << 4;

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

        int i = Tools.byteArrayToIntHighToLow(bytes_no);
        System.out.println(i);
        System.out.println("16:" + Integer.toString(i, 16));

        byte[] bytes_no_ = Tools.intToByteArrayHighToLow(i);
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
