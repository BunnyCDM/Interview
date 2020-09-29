package com.example.socket.L4;

/**
 * Created by mac on 2019/7/14.
 */
public class Tools {

    /**
     * byte 数组 转 int ，高位在前，低位在后
     */
    public static int byteArrayToIntHighToLow(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    /**
     * int 转byte 数组 高位在前，低位在后
     */
    public static byte[] intToByteArrayHighToLow(int a) {
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
     */
    public static byte[] intToByteArrayIntLowToHigh(int a) {
        //00000000 00000000 00000000 00000011
        byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            int n = bytes.length - 1 - i;
            bytes[4 - i - 1] = (byte) ((a >> 8 * n));
        }
        return bytes;

    }


    /**
     * 将一个小于 255 的 int 转化成 byte
     */
    public static byte intToByte(int value) throws Exception {
        int byteint = value & 0xff;
        return new Integer(byteint).byteValue();

//        if (value > 255) {
//            throw new Exception("can not convert int value to byte when int value is greater than" +
//                    " 255");
//        }
//        return (byte) value;
    }

    /**
     * byte 转 int,无符号的转
     */
    public static int byteToInt(byte b) {
        return b & 0xff;
    }




    /**
     * 单字节数组转换为十六进制字符串,每个字节由两个字符表示，位数不够，高位补0
     * byte[] bytes=new byte[]{0, 0, 1, 96};
     * byte b=bytes[3];
     * b=96
     */
    public static final String byteToHexString(byte b) {
        StringBuilder sb = new StringBuilder();
        int i = b & 0xff;
        String hexString = Integer.toHexString(i);
        if (hexString.length() == 1) {
            sb.append("0");
        }
        sb.append(hexString);
        return sb.toString().toUpperCase();
    }

    /**
     * 字节数组转换为十六进制字符串
     */
    public static String byteArrayToHexString(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            //throw new IllegalArgumentException(" byte array is null! ");
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }


    /**
     * 十六进制串转化为单byte
     */
    public static final byte hexStringToByte(String hex) throws IllegalArgumentException {
//        if (hex.length() % 2 != 0) {
//            throw new IllegalArgumentException();
//        }

        int byteInt = Integer.parseInt(hex, 16) & 0xFF;
        byte reByte = new Integer(byteInt).byteValue();
        return reByte;
    }

    /**
     * 十六进制串转化为byte数组
     */
    public static final byte[] hexStringToByteArray(String hex) throws IllegalArgumentException {
        if (hex == null || hex.length() <= 0) {
            return null;
        }
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteInt = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteInt).byteValue();
        }
        return b;

//        if (hexString.length() % 2 != 0) {
//            throw new IllegalArgumentException();
//        }
//
//        hexString = hexString.toLowerCase();//小写
//
//        if ("0x".equals(hexString.substring(0, 2))) {
//            hexString = hexString.substring(2);//去掉十六机制前缀
//        }
//
//        int length = hexString.length() / 2;
//
//        final byte[] byteArray = new byte[length];
//        int k = 0;
//        for (int i = 0; i < byteArray.length; i++) {
//            //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
//            //将hex 转换成byte   "&" 操作为了防止负数的自动扩展
//            // hex转换成byte 其实只占用了4位，然后把高位进行右移四位
//            // 然后“|”操作  低四位 就能得到 两个 16进制数转换成一个byte.
//            //
//            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
//            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
//            byteArray[i] = (byte) (high << 4 | low);
//            k += 2;
//        }
//        return byteArray;
    }


    /**
     * 两个byte 数组叠加. 将 desBytes 添加到 srcBytes
     *
     * @param srcBytes 被添加的byte s数组
     * @param desBytes 　添加的byte 数组
     * @return byte[]　返回添加后的数组
     */
    public static byte[] addBytes(byte[] srcBytes, byte[] desBytes) {
        byte[] returnArray = new byte[srcBytes.length + desBytes.length];
        System.arraycopy(srcBytes, 0, returnArray, 0, srcBytes.length);
        System.arraycopy(desBytes, 0, returnArray, srcBytes.length, desBytes.length);
        return returnArray;
    }


}

