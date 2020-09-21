package com.example.socket.L4;

import java.io.FileOutputStream;

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
//        fos.write(a>>>24);
//        fos.write(a>>>16);
//        fos.write(a>>>8);
        //write 只能写八位，那么写一个int需要写4次每次8位
//        fos.write(a);
    }
}

