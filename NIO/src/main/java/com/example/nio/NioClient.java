package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * NIO客户端
 * <p>
 * implements Closeable使用意义
 */
public class NioClient {


    public static void main(String[] args) throws IOException {
//        new NioClient().start();
    }

    /**
     * 启动
     */
    public void start(String nickname) throws IOException {
        /**
         * 连接服务器端
         */
        //SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 20001));
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("192.168.1.5", 20000));

        /**
         * 接收服务器端响应
         */
        // 新开线程，专门负责来接收服务器端的响应数据
        // selector ， socketChannel ， 注册
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

        /**
         * 向服务器端发送数据
         */
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();
            if (request != null && request.length() > 0) {
                System.out.println(request);
//                socketChannel.write(
//                        Charset.forName("UTF-8")
//                                .encode(nickname + " : " + request));

                byte[] buffer = new byte[256];
                //ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                System.out.println("byteBuffer=" + byteBuffer);

                // byte
                byteBuffer.put((byte) 126);
                System.out.println("byteBuffer（byte）=" + byteBuffer);

                // char
                char c = 'a';
                byteBuffer.putChar(c);
                System.out.println("byteBuffer（char）=" + byteBuffer);

                // int
                int i = 2323123;
                byteBuffer.putInt(i);
                System.out.println("byteBuffer（int）=" + byteBuffer);

                // bool
                boolean b = true;
                byteBuffer.put(b ? (byte) 1 : (byte) 0);
                System.out.println("byteBuffer（bool）=" + byteBuffer);

                // Long
                long l = 298789739;
                byteBuffer.putLong(l);
                System.out.println("byteBuffer（Long）=" + byteBuffer);

                // float
                float f = 12.345f;
                byteBuffer.putFloat(f);
                System.out.println("byteBuffer（float）=" + byteBuffer);


                // double
                double d = 13.31241248782973;
                byteBuffer.putDouble(d);
                System.out.println("byteBuffer（double）=" + byteBuffer);

                // String
                String str = "Hello你好！";
                byteBuffer.put(str.getBytes());
                System.out.println("byteBuffer（String）=" + byteBuffer);

                socketChannel.write(byteBuffer);
            }
        }

    }

}
