package com.example.socket.L5_TCP_Channel.client;

import com.example.socket.L5_TCP_Channel.client.bean.ServerInfo;

import java.io.IOException;

/**
 * Created by mac on 2019/7/14.
 */
public class Client {

    public static void main(String[] args) {

        ServerInfo info = UDPSearcher.searchServer(10000);
        System.out.println("Server:" + info);

        if (info != null) {
            try {
                TCPClient.linkWith(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
