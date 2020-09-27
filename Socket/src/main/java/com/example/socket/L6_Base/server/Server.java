package com.example.socket.L6_Base.server;

import com.example.socket.L6_Base.clink.core.IoContext;
import com.example.socket.L6_Base.clink.impl.IoSelectorProvider;
import com.example.socket.L6_Base.foo.Foo;
import com.example.socket.L6_Base.foo.TCPConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mac on 2020-09-22.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        File cachePath = Foo.getCacheDir("server");

        IoContext.setup()
                .ioProvider(new IoSelectorProvider())
                .start();

        TCPServer tcpServer = new TCPServer(TCPConstants.PORT_SERVER,cachePath);
        boolean isSucceed = tcpServer.start();
        if (!isSucceed) {
            System.out.println("Start TCP server failed!");
            return;
        }


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        do {
            str = bufferedReader.readLine();
            if ("00bye00".equalsIgnoreCase(str)) {
                break;
            }

            tcpServer.broadcast(str);
        } while (true);

        tcpServer.stop();

        IoContext.close();
    }


}
