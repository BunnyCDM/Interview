package com.example.socket.L6_Base.server;

import com.example.socket.L6_Base.clink.core.Connector;
import com.example.socket.L6_Base.clink.utils.CloseUtils;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Created by mac on 2020-09-22.
 */
public class ClientHandler extends Connector {

    private final ClientHandlerCallback clientHandlerCallback;
    private final String clientInfo;

    public ClientHandler(SocketChannel socketChannel, ClientHandlerCallback clientHandlerCallback) throws IOException {
        this.clientHandlerCallback = clientHandlerCallback;
        this.clientInfo = socketChannel.getRemoteAddress().toString();

        System.out.println("新客户端连接：" + clientInfo);
        //this.clientInfo = "A[" + socket.getInetAddress().getHostAddress() + "]" + " P[" + socket.getPort() + "]";

        setup(socketChannel);
    }

    private void exitBySelf() {
        exit();
        clientHandlerCallback.onSelfClosed(this);
    }

    public void exit() {
        CloseUtils.close(this);
        System.out.println("客户端已退出：" + clientInfo);
        //System.out.println("客户端已退出：" + socket.getInetAddress() + " P:" + socket.getPort());
    }

    @Override
    public void onChannelClosed(SocketChannel channel) {
        super.onChannelClosed(channel);
        exitBySelf();
    }

    @Override
    protected void onReceiveNewMessage(String str) {
        super.onReceiveNewMessage(str);
        clientHandlerCallback.onNewMessageArrived(this, str);
    }

    public interface ClientHandlerCallback {
        void onSelfClosed(ClientHandler handler);

        void onNewMessageArrived(ClientHandler handler, String msg);
    }

}
