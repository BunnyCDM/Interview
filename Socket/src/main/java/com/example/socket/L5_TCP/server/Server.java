package com.example.socket.L5_TCP.server;

import com.example.socket.L5_TCP.constants.TCPConstants;

import java.io.IOException;

/**
 * Created by mac on 2019/7/14.
 */
public class Server {

    public static void main(String[] args) {

        TCPServer tcpServer = new TCPServer(TCPConstants.PORT_SERVER);
        boolean isSucceed = tcpServer.start();
        if (!isSucceed) {
            System.out.println("Start TCP server failed!");
            return;
        }

        UDPProvider.start(TCPConstants.PORT_SERVER);

        try {
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UDPProvider.stop();
        tcpServer.stop();
    }
}

