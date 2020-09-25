package com.example.socket.L6_Base.client;

import com.example.socket.L6_Base.clink.core.Connector;
import com.example.socket.L6_Base.clink.utils.CloseUtils;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by mac on 2020-09-22.
 */
public class TCPClient extends Connector {

    public TCPClient(SocketChannel socketChannel) throws IOException {
        setup(socketChannel);
    }

    public void exit() {
        CloseUtils.close(this);
    }

    @Override
    public void onChannelClosed(SocketChannel channel) {
        super.onChannelClosed(channel);
        System.out.println("链接已关闭，无法读取数据！");
    }

    //父类已经做了，在这里就不做了
    @Override
    protected void onReceiveNewMessage(String str) {
        super.onReceiveNewMessage(str);
    }

    public static TCPClient startWith(int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        // 连接本地，端口2000；超时时间3000ms
        socketChannel.connect(new InetSocketAddress(Inet4Address.getLocalHost(), port));

        System.out.println("已发起服务器连接，并进入后续流程～");
        System.out.println("客户端信息：" + socketChannel.getLocalAddress().toString());
        System.out.println("服务器信息：" + socketChannel.getRemoteAddress().toString());

        try {
            return new TCPClient(socketChannel);
        } catch (Exception e) {
            System.out.println("连接异常");
            CloseUtils.close(socketChannel);
        }
        return null;
    }


}
