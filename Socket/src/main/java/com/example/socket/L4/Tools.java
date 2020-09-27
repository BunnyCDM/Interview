package com.example.socket.L4;

/**
 * Created by mac on 2019/7/14.
 */
public class Tools {

    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        //00000000 00000000 00000000 00000011
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };


//        int a=10;
//        FileOutputStream fos=new FileOutputStream();
//        fos.writeTo(a>>>24);
//        fos.writeTo(a>>>16);
//        fos.writeTo(a>>>8);
        //writeTo 只能写八位，那么写一个int需要写4次每次8位
//        fos.writeTo(a);
    }


    /**
     * byte 数组 转 int ，低位在前，高位在后。
     *
     * @param b byte 数组
     * @return int value 如果长度大于４，那么直接返回0
     */
    public static int byteArrayToIntLowToHigh(byte[] b) {
        int returnValue = 0;
        if (b.length > 4) {
            //数组太大，直接返回0
            return returnValue;
        }
        for (int i = 0; i < b.length; i++) {
            int leftOffset = i * 8;
            returnValue = returnValue + ((b[i] & 0xff) << leftOffset);
        }
        return returnValue;

//        int value;
//        value = (int) ((src[0] & 0xFF)
//                | ((src[1] & 0xFF)<<8)
//                | ((src[2] & 0xFF)<<16)
//                | ((src[3] & 0xFF)<<24));
//        return value;
    }


    /**
     * int 数组 转 byte ，低位在前，高位在后。
     *
     * @param a
     * @return
     */
    public static byte[] intToByteArrayIntLowToHigh(int a) {
        //00000000 00000000 00000000 00000011
        byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++){
            int n = bytes.length - 1 -i;
            bytes[4 - i - 1] = (byte)((a>>8*n));
        }
        return bytes;

    }

}

