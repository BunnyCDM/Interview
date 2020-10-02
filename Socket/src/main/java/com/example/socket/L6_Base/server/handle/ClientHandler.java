package com.example.socket.L6_Base.server.handle;

import com.example.socket.L6_Base.clink.core.Connector;
import com.example.socket.L6_Base.clink.core.Packet;
import com.example.socket.L6_Base.clink.core.ReceivePacket;
import com.example.socket.L6_Base.clink.utils.CloseUtils;
import com.example.socket.L6_Base.foo.Foo;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Created by mac on 2020-09-22.
 */
public class ClientHandler extends Connector {
    private final File cachePath;
    private final ClientHandlerCallback clientHandlerCallback;
    private final String clientInfo;

    public ClientHandler(SocketChannel socketChannel, ClientHandlerCallback clientHandlerCallback,File cachePath) throws IOException {
        this.clientHandlerCallback = clientHandlerCallback;
        this.clientInfo = socketChannel.getRemoteAddress().toString();
        this.cachePath = cachePath;

        System.out.println("新客户端连接：" + clientInfo);
        //this.clientInfo = "A[" + socket.getInetAddress().getHostAddress() + "]" + " P[" + socket.getPort() + "]";

        setup(socketChannel);
    }

    public String getClientInfo(){
        return  clientInfo;
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
    protected File createNewReceiveFile() {
        return Foo.createRandomTemp(cachePath);
    }

    @Override
    protected void onReceivedPacket(ReceivePacket packet) {
        super.onReceivedPacket(packet);
        if (packet.type() == Packet.TYPE_MEMORY_STRING) {
            String string = (String) packet.entity();
            System.out.println(key.toString() + ":" + string);
            clientHandlerCallback.onNewMessageArrived(this, string);
        }
    }

    public interface ClientHandlerCallback {
        void onSelfClosed(ClientHandler handler);

        void onNewMessageArrived(ClientHandler handler, String msg);
    }

}
