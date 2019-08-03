package com.example.socket.L5_UDP.constants;

/**
 * Created by mac on 2019/7/14.
 */
public class UDPConstants {

    // 公用头部
    public final static byte[] HEADER = new byte[]{7, 7, 7, 7, 7, 7, 7, 7};

    // 服务器固化UDP接收端口
    public final static int PORT_SERVER = 30201;

    // 客户端回送端口
    public final static int PORT_CLIENT_RESPONSE = 30202;

}
