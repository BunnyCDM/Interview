package com.example.socket.L6_Base.clink;

/**
 * Created by mac on 2020-09-21.
 */
public class ByteUtils {

    public static boolean startsWith(byte[] source, byte[] match) {
        return startsWith(source, 0, match);
    }

    public static boolean startsWith(byte[] source, int offset, byte[] match) {

        if (match.length > (source.length - offset)) {
            return false;
        }

        for (int i = 0; i < match.length; i++) {
            if (source[offset + i] != match[i]) {
                return false;
            }
        }

        return true;
    }


    public static boolean equals(byte[] source, byte[] match) {
        if (match.length != source.length) {
            return false;
        }

        return startsWith(source, 0, match);
    }


    public static void getBytes(byte[] source, int srcBegin, int srcEnd, byte[] destination,
                                int dstBegin) {
        /**
         * 将指定源数组的数组 从指定位置 复制到目标数组的指定位置
         *
         * src:源数组
         * srcPos:源数组的起始位置
         * dest:目标数组
         * destPos:目的地数据的起始位置
         * length:要复制的源数组元素的数量
         */
        System.arraycopy(source, srcBegin, destination, dstBegin, srcEnd - srcBegin);
    }

    public static byte[] subbytes(byte[] source, int srcBegin, int srcEnd) {
        byte destination[];

        destination = new byte[srcEnd - srcBegin];
        getBytes(source, srcBegin, srcEnd, destination, 0);

        return destination;
    }


    public static byte[] subbytes(byte[] source, int srcBegin) {
        return subbytes(source, srcBegin, source.length);
    }

}
