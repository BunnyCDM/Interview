package com.example.socket.L5_UDP.server;

import com.example.socket.L5_UDP.constants.TCPConstants;

import java.io.IOException;

/**
 * Created by mac on 2019/7/14.
 */
public class Server {

    public static void main(String[] args) {

        ServerProvider.start(TCPConstants.PORT_SERVER);

        try {
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ServerProvider.stop();
    }
}
