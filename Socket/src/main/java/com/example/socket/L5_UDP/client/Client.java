package com.example.socket.L5_UDP.client;

import com.example.socket.L5_UDP.client.bean.ServerInfo;

/**
 * Created by mac on 2019/7/14.
 */
public class Client {

    public static void main(String[] args) {

        ServerInfo info = ClientSearcher.searchServer(10000);
        System.out.println("Server:" + info);

    }
}
