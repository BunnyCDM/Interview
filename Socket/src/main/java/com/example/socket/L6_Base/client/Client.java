package com.example.socket.L6_Base.client;

import com.example.socket.L6_Base.clink.box.FileSendPacket;
import com.example.socket.L6_Base.clink.core.IoContext;
import com.example.socket.L6_Base.clink.impl.IoSelectorProvider;
import com.example.socket.L6_Base.foo.Foo;
import com.example.socket.L6_Base.foo.TCPConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mac on 2020-09-22.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        try {
            File cachePath = Foo.getCacheDir("client");
            IoContext.setup()
                    .ioProvider(new IoSelectorProvider())
                    .start();

            TCPClient tcpClient = TCPClient.startWith(TCPConstants.PORT_SERVER,cachePath);
            if (tcpClient == null) {
                return;
            }

            write(tcpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }

        IoContext.close();
    }

    private static void write(TCPClient tcpClient) throws IOException {
        // 构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        do {
            // 键盘读取一行
            String str = input.readLine();
            if ("00bye00".equalsIgnoreCase(str)) {
                break;
            }

            // --f url
            if (str.startsWith("--f")) {
                String[] array = str.split(" ");
                if (array.length >= 2) {
                    String filePath = array[1];
                    File file = new File(filePath);
                    if (file.exists() && file.isFile()) {
                        FileSendPacket packet = new FileSendPacket(file);
                        tcpClient.send(packet);
                        continue;
                    }
                }
            }

            // 发送字符串
            tcpClient.send(str);
            /**
             * 多消息粘包复现测试（客户端）
             */
//            tcpClient.send(str);
//            tcpClient.send(str);
//            tcpClient.send(str);


        } while (true);
    }

}
