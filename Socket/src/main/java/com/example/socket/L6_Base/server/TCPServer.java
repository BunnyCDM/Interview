package com.example.socket.L6_Base.server;

import com.example.socket.L6_Base.clink.core.ScheduleJob;
import com.example.socket.L6_Base.clink.core.schedule.IdleTimeoutScheduleJob;
import com.example.socket.L6_Base.clink.utils.CloseUtils;
import com.example.socket.L6_Base.server.handle.ClientHandler;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by mac on 2020-09-22.
 */
public class TCPServer implements ClientHandler.ClientHandlerCallback, ServerAcceptor.AcceptListener {

    private final int port;
    private final File cachePath;
    private final ExecutorService forwardingThreadPoolExecutor;
    private List<ClientHandler> clientHandlerList = new ArrayList<>();
    private ServerAcceptor acceptor;
    private ServerSocketChannel server;


    public TCPServer(int port, File cachePath) {
        this.port = port;
        this.cachePath = cachePath;
        // 转发线程池
        this.forwardingThreadPoolExecutor = Executors.newSingleThreadExecutor();
    }

    public boolean start() {
        try {
            ServerAcceptor acceptor = new ServerAcceptor(this);

            ServerSocketChannel server = ServerSocketChannel.open();
            // 设置为非阻塞
            server.configureBlocking(false);
            // 绑定本地端口
            server.socket().bind(new InetSocketAddress(port));
            // 注册客户端连接到达监听
            server.register(acceptor.getSelector(), SelectionKey.OP_ACCEPT);
            this.server = server;
            this.acceptor = acceptor;

            acceptor.start();
            if (acceptor.awaitRunning()) {
                System.out.println("服务器准备就绪～");
                System.out.println("服务器信息：" + server.getLocalAddress().toString());
                //System.out.println("服务器信息：" + server.getInetAddress() + " P:" + server.getLocalPort());
                return true;
            } else {
                System.out.println("启动异常！");
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void stop() {
        if (acceptor != null) {
            acceptor.exit();
        }

        CloseUtils.close(server);

        synchronized (TCPServer.this) {
            for (ClientHandler clientHandler : clientHandlerList) {
                clientHandler.exit();
            }

            clientHandlerList.clear();
        }

        //停止线程池
        forwardingThreadPoolExecutor.shutdownNow();
    }

    public synchronized void broadcast(String str) {
        for (ClientHandler clientHandler : clientHandlerList) {
            clientHandler.send(str);
        }
    }

    @Override
    public synchronized void onSelfClosed(ClientHandler handler) {
        clientHandlerList.remove(handler);
    }

    @Override
    public void onNewMessageArrived(final ClientHandler handler, final String msg) {
        //System.out.println("Received-" + handler.getClientInfo() + ":" + msg);
        /**
         * 多消息粘包复现测试（服务端）
         */
//        System.out.println("Received-" + handler.getClientInfo() + ":" + msg.replace("\n","--"));

        forwardingThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (TCPServer.this) {
                    for (ClientHandler clientHandler : clientHandlerList) {
                        if (clientHandler.equals(handler)) {
                            continue;
                        }
                        clientHandler.send(msg);
                    }
                }
            }
        });
    }

    @Override
    public void onNewSocketArrived(SocketChannel channel) {
        try {
            ClientHandler clientHandler = new ClientHandler(channel, this, cachePath);
            System.out.println(clientHandler.getClientInfo() + ":Connected!");

            // 空闲任务调度
            ScheduleJob scheduleJob = new IdleTimeoutScheduleJob(20, TimeUnit.SECONDS, clientHandler);
            clientHandler.schedule(scheduleJob);

            synchronized (TCPServer.this) {
                clientHandlerList.add(clientHandler);
                System.out.println("当前客户端数量：" + clientHandlerList.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端链接异常：" + e.getMessage());
        }
    }
}
