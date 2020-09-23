package com.example.socket.L6_Base.client;

import com.example.socket.L6_Base.foo.TCPConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mac on 2020-09-22.
 */
public class Client {

    public static void main(String[] args) {
        try {

            TCPClient tcpClient = TCPClient.startWith(TCPConstants.PORT_SERVER);
            if (tcpClient == null) {
                return;
            }

            write(tcpClient);
        } catch (IOException e) {
            e.printStackTrace();
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
            tcpClient.send(str);
            /**
             * 多消息粘包复现测试（客户端）
             */
//            tcpClient.send(str);
//            tcpClient.send(str);
//            tcpClient.send(str);

            if ("00bye00".equalsIgnoreCase(str)) {
                break;
            }
        } while (true);
    }

}
