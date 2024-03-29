package com.example.socket.L4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by mac on 2019/7/14.
 */
public class Server {

    private static final int PORT = 20004;

    public static void main(String[] args) throws IOException {

        ServerSocket server = createServerSocket();

        initServerSocket(server);

        // 绑定到本地端口上
        server.bind(new InetSocketAddress(Inet4Address.getLocalHost(), PORT), 50);


        System.out.println("服务器准备就绪～");
        System.out.println("服务器信息：" + server.getInetAddress() + " P:" + server.getLocalPort());


        // 等待客户端连接
        for (; ; ) {
            // 得到客户端
            Socket client = server.accept();
            // 客户端构建异步线程
            ClientHandler clientHandler = new ClientHandler(client);
            // 启动线程
            clientHandler.start();
        }

    }

    private static ServerSocket createServerSocket() throws IOException {
        // 创建基础的ServerSocket
        ServerSocket serverSocket = new ServerSocket();

        // 绑定到本地端口20000上，并且设置当前可允许等待链接的队列为50个
        //serverSocket = new ServerSocket(PORT);

        // 等效于上面的方案，队列设置为50个
        //serverSocket = new ServerSocket(PORT, 50);

        // 与上面等同
        // serverSocket = new ServerSocket(PORT, 50, Inet4Address.getLocalHost());

        return serverSocket;
    }

    private static void initServerSocket(ServerSocket serverSocket) throws IOException {
        // 是否复用未完全关闭的地址端口
        serverSocket.setReuseAddress(true);

        // 等效Socket#setReceiveBufferSize
        serverSocket.setReceiveBufferSize(64 * 1024 * 1024);

        // 设置serverSocket#accept超时时间
        // serverSocket.setSoTimeout(2000);

        // 设置性能参数：短链接，延迟，带宽的相对重要性
        serverSocket.setPerformancePreferences(1, 1, 1);
    }

    /**
     * 客户端消息处理
     */
    private static class ClientHandler extends Thread {
        private Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("新客户端连接：" + socket.getInetAddress() +
                    " P:" + socket.getPort());

            try {
                // 得到套接字流
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                byte[] buffer = new byte[256];
                int readCount = inputStream.read(buffer);
                System.out.println("readCount="+readCount);
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, 0, readCount);
                System.out.println("byteBuffer="+byteBuffer);

                // byte
                byte be = byteBuffer.get();
                System.out.println("byteBuffer(byte)="+byteBuffer);

                // char
                char c = byteBuffer.getChar();
                System.out.println("byteBuffer(char)="+byteBuffer);

                // int
                int i = byteBuffer.getInt();
                System.out.println("byteBuffer(int)="+byteBuffer);

                // bool
                boolean b = byteBuffer.get() == 1;
                System.out.println("byteBuffer(bool)="+byteBuffer);

                // Long
                long l = byteBuffer.getLong();
                System.out.println("byteBuffer(Long)="+byteBuffer);

                // float
                float f = byteBuffer.getFloat();
                System.out.println("byteBuffer(float)="+byteBuffer);

                // double
                double d = byteBuffer.getDouble();
                System.out.println("byteBuffer(double)="+byteBuffer);

                // String
                int pos = byteBuffer.position();
                String str = new String(buffer, pos, readCount - pos - 1);
                System.out.println("byteBuffer(String)="+byteBuffer);

                System.out.println("收到数量：" + readCount + " 数据："
                        + be + "\n"
                        + c + "\n"
                        + i + "\n"
                        + b + "\n"
                        + l + "\n"
                        + f + "\n"
                        + d + "\n"
                        + str + "\n");

                outputStream.write(buffer, 0, readCount);
                System.out.println("byteBuffer="+byteBuffer);
                byteBuffer.flip();
                System.out.println("byteBuffer(flip)="+byteBuffer);
                outputStream.close();
                inputStream.close();

            } catch (Exception e) {
                System.out.println("连接异常断开");
            } finally {
                // 连接关闭
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("客户端已退出：" + socket.getInetAddress() +
                    " P:" + socket.getPort());

        }
    }
}

