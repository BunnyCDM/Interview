package com.kl.io;

/**
 * Created by mac on 2020-09-21.
 */
public class EncodeDemo {


    public static void main(String args[]) throws Exception {

        String s = "慕课ABC";
        byte[] bytes1 = s.getBytes();
        for (byte b : bytes1) {
            /**
             * ffffffe6
             * ffffff85
             * ffffff95
             * ffffffe8
             * ffffffaf
             * ffffffbe
             * 41
             * 42
             * 43
             */
            //System.out.println(Integer.toHexString(b));

            /**
             * e6
             * 85
             * 95
             * e8
             * af
             * be
             * 41
             * 42
             * 43
             */
            System.out.println(Integer.toHexString(b & 0xff) + "");//前面24个零去掉
        }


        System.out.println();
        byte[] bytes2 = s.getBytes("gbk");
        for (byte b : bytes2) {
            /**
             * c4
             * bd
             * bf
             * ce
             * 41
             * 42
             * 43
             */
            System.out.println(Integer.toHexString(b & 0xff) + "");//前面24个零去掉
        }


        System.out.println();
        byte[] bytes3 = s.getBytes("utf-16be");
        for (byte b : bytes3) {
            /**
             * 61
             * 55
             * 8b
             * fe
             * 0
             * 41
             * 0
             * 42
             * 0
             * 43
             */
            System.out.println(Integer.toHexString(b & 0xff) + "");//前面24个零去掉
        }

        System.out.println();

        String str1 = new String(bytes1);
        System.out.println("str1 is " + str1);
        String str2 = new String(bytes2, "gbk");
        System.out.println("str2 is " + str2);

    }

}
