package com.example.socket.L6_Base.client;

import com.example.socket.L6_Base.foo.TCPConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2020-09-22.
 */
public class ClientTest {

    private static boolean done;

    public static void main(String[] args) throws IOException {

        // 当前连接数量
        int size = 0;
        final List<TCPClient> tcpClients = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                TCPClient tcpClient = TCPClient.startWith(TCPConstants.PORT_SERVER);
                if (tcpClient == null) {
                    System.out.println("连接异常");
                    continue;
                }

                tcpClients.add(tcpClient);

                System.out.println("连接成功：" + (++size));

            } catch (IOException e) {
                System.out.println("连接异常");
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.in.read();

        Runnable runnable = () -> {
            while (!done) {
                for (TCPClient tcpClient : tcpClients) {
                    tcpClient.send("Hello~");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.in.read();

        // 等待线程完成
        done = true;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 客户端结束操作
        for (TCPClient tcpClient : tcpClients) {
            tcpClient.exit();
        }

    }


    private static void write(TCPClient tcpClient) throws IOException {
        // 构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        do {
            // 键盘读取一行
            String str = input.readLine();
            // 发送到服务器
            tcpClient.send("Hello~");

            if ("00bye00".equalsIgnoreCase(str)) {
                break;
            }
        } while (true);
    }

}