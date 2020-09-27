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

    /**
     * 将 一个desByte 添加到 srcBytes
     *
     * @param srcBytes 　被添加的byte 数组
     * @param desByte  　添加的byte 数组
     * @return byte[]　返回添加后的数组
     */
    public static byte[] addBytes(byte[] srcBytes, byte desByte) {
        byte[] desByteArray = new byte[]{desByte};
        return addBytes(srcBytes, desByteArray);
    }

    /**
     * int 转byte 数组 高位在前，低位在后
     *
     * @param res int value
     * @return byte array value of int ,high to low,
     */
    public static byte[] intToByteArrayHighToLow(int res) {
        byte[] targets = new byte[4];

        targets[3] = (byte) (res & 0xff);// 最低位
        targets[2] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[1] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[0] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
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
    }

    public static void main(String[] args) {
        byte[] bytes=hexStringToByteArray("B07099B5");
        System.out.println(Arrays.toString(bytes));

        //byte[] bytes_=new byte[]{ 16, 3, 16, 7};
        byte[] bytes_=new byte[]{2,33, 8, 35};
        //byte[] bytes_=new byte[]{115, 49, 32, 9, 35, 0, 0, 16, 3, 16, 7, 2, 33, 8, 35, 0};
        System.out.println(Tools.byteArrayToInt(bytes_));
        System.out.println(byteArrayToHex(bytes_));


    }

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
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b 需要转换的字节
     * @return String 十六进制字符串
     */
    public static final String byteToHex(byte b) {
        StringBuilder sb = new StringBuilder();
        int i = b & 0xff;
        String stmp = Integer.toHexString(i);
        if (stmp.length() == 1) {
            sb.append("0").append(stmp);
        } else {
            sb.append(stmp);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static String byteArrayToHex(byte[] b) {
        if (b == null) {
            throw new IllegalArgumentException(
                    "Argument b ( byte array ) is null! ");
        }
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xff);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 将byte 数组 转化成 hex string
     *
     * @param src 　byte 数组
     * @return String　hex string
     */
    public static String byteArrayToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * byte array 转化成　hex String
     *
     * @param bytes 源
     * @return String　hex
     */
    public static String byteArrayToHexArray(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 将一个byte 转化成 十六进制的 字符串
     * 此方法依赖了{@link DatatypeConverter#printHexBinary(byte[])}
     *
     * @param bte 　被转化的字节
     * @return String 用大写表示
     */
    public static String byteToHexString(byte bte) {
        String hexString = byteToHex(bte);
        //String hexString = DatatypeConverter.printHexBinary(new byte[]{bte});
        return hexString.toUpperCase();
    }

    /**
     * 长度为2的十六进制串转化为单byte
     *
     * @param hex 被转化的hex string,长度为２
     * @return the array of byte
     */
    public static final byte hexStringToByte(String hex)
            throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }

        int byteint = Integer.parseInt(hex, 16) & 0xFF;
        byte reByte = new Integer(byteint).byteValue();
        return reByte;
    }

    /**
     * 十六进制串转化为byte数组
     *
     * @param hex 被转化的hex string
     * @return the array of byte
     */
    public static final byte[] hexStringToByteArray(String hex)
            throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    private static void test7() {
        //3 = 二进制=00000011，十六进制=3
        System.out.println(Arrays.toString(intToByteArray(3)));//[0, 0, 0, 3]
        System.out.println(byteArrayToInt(intToByteArray(3)));//3

        //352 ：二进制=【00000000 00000000 00000001 01100000】，十六进制=160
        byte[] bytes = intToByteArray(352);
        System.out.println(Arrays.toString(bytes));//[0, 0, 1, 96]
        System.out.println(byteArrayToInt(intToByteArray(352)));//352

        System.out.println(byteToHex(bytes[3]));//60
        System.out.println(byteArrayToHex(bytes));//00000160
        System.out.println(byteArrayToHexString(bytes));//00000160
        System.out.println(byteArrayToHexArray(bytes));//00000160

        System.out.println(hexStringToByte("60"));//00000160
        System.out.println(Arrays.toString(hexStringToByteArray("00000160")));//00000160
        System.out.println(Arrays.toString(hexStringToBytes("00000160")));//00000160
        //String cmd = "00A40000023F0100";
        //byte[] bytes = new byte[]{0, -92, 0, 0, 2, 63, 1, 0};

        byte[] bytes1 = new byte[]{0, -80, -107, 0};
        System.out.println(byteArrayToHexArray(bytes1));
        //System.out.println(Arrays.toString(hexStringToByteArray("00000000899666660000000000000000000 0350520200 924205009240000")));

        byte[] bytes2 = new byte[]{0, 0, 53, 5};
        System.out.println(byteArrayToHexArray(bytes2));

    }

    public static byte[] hexStringToBytes(String hexString) {

        hexString = hexString.toLowerCase();//小写

        if ("0x".equals(hexString.substring(0, 2))) {
            hexString = hexString.substring(2);//去掉十六机制前缀
        }
        int length = hexString.length() / 2;

        final byte[] byteArray = new byte[length];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {
            //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            //将hex 转换成byte   "&" 操作为了防止负数的自动扩展
            // hex转换成byte 其实只占用了4位，然后把高位进行右移四位
            // 然后“|”操作  低四位 就能得到 两个 16进制数转换成一个byte.
            //
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;

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
