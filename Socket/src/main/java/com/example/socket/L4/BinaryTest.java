package com.example.socket.L4;

import java.util.Arrays;

/**
 * Created by mac on 2020-04-26.
 */
public class BinaryTest {


    public static void main(String[] args) {
        int bin = 0b1100010;
        int oct = 0142;
        int hex = 0x62;
        int dec = 98;

        /**
         * 前缀是数字0，不是字母o
         * 英文字母b、x是不区分大小写的
         * 在指定进制中使用规定的数码
         * 底层存储都是二进制的形式
         * java默认使用十进制，输出显示都是十进制的形式
         */

        System.out.println("2:" + bin);
        System.out.println("8:" + oct);
        System.out.println("16:" + hex);
        System.out.println("10:" + dec);

        System.out.println("=============================");
        int num = 98;
        System.out.println("2:" + Integer.toBinaryString(num));
        System.out.println("8:" + Integer.toOctalString(num));
        System.out.println("16:" + Integer.toHexString(num));

        System.out.println("==============转化任意进制，范围2到32===============");
        System.out.println("2:" + Integer.toString(num, 2));
        System.out.println("8:" + Integer.toString(num, 8));
        System.out.println("16:" + Integer.toString(num, 16));


        System.out.println("====String转换,parseInt,valueOf返回类型不一样=======");
        System.out.println(Integer.parseInt("1100010", 2));
        System.out.println(Integer.parseInt("62", 16));
        System.out.println(Integer.valueOf("1100010", 2));
        System.out.println(Integer.valueOf("62", 16));

        //00000011
        byte[] bytes = Tools.intToByteArray(3);
        System.out.println("bytes is " + Arrays.toString(bytes));

        int i = Tools.byteArrayToInt(bytes);
        System.out.println("i is " + i);

    }

}
