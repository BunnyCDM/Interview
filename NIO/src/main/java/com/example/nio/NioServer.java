package com.example.nio;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/**
 * NIO服务器端
 * <p>
 * NIO SelectionKey中定义的4种事件
 * SelectionKey.OP_ACCEPT —— 接收连接继续事件，表示服务器监听到了客户连接，服务器可以接收这个连接了
 * SelectionKey.OP_CONNECT —— 连接就绪事件，表示客户与服务器的连接已经建立成功
 * SelectionKey.OP_READ —— 读就绪事件，表示通道中已经有了可读的数据，可以执行读操作了（通道目前有数据，可以进行读操作了）
 * SelectionKey.OP_WRITE —— 写就绪事件，表示已经可以向通道写数据了（通道目前可以用于写操作）
 * 这里 注意，下面两种，SelectionKey.OP_READ ，SelectionKey.OP_WRITE ，
 * <p>
 * 1.当向通道中注册SelectionKey.OP_READ事件后，如果客户端有向缓存中write数据，下次轮询时，则会 isReadable()=true；
 * <p>
 * 2.当向通道中注册SelectionKey.OP_WRITE事件后，这时你会发现当前轮询线程中isWritable()一直为ture，如果不设置为其他事件
 */
public class NioServer {

    /**
     * 主方法
     *
     * @param args
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) throws IOException {
        new NioServer().start();
    }

    /**
     * 启动
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void start() throws IOException {
        /**
         * 1. 创建Selector
         */
        Selector selector = Selector.open();

        /**
         * 2. 通过ServerSocketChannel创建channel通道
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        /**
         * 3. 为channel通道绑定监听端口
         */
        serverSocketChannel.bind(new InetSocketAddress(8001));

        /**
         * 4. **设置channel为非阻塞模式**
         */
        serverSocketChannel.configureBlocking(false);

        /**
         * 5. 将channel注册到selector上，监听连接事件
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//接收连接事件
        System.out.println("服务器启动成功！");

        /**
         * 6. 循环等待新接入的连接
         */
        for (; ; ) { // while(true) c for;;
            /**
             * TODO 获取可用channel数量
             */
            int readyChannels = selector.select();

            /**
             * TODO 为什么要这样！！？
             */
            if (readyChannels == 0) continue;

            /**
             * 获取可用channel的集合
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                /**
                 * selectionKey实例
                 */
                SelectionKey selectionKey = (SelectionKey) iterator.next();

                /**
                 * **移除Set中的当前selectionKey**
                 */
                iterator.remove();

                /**
                 * 7. 根据就绪状态，调用对应方法处理业务逻辑
                 */
                /**
                 * 如果是 接入事件
                 */
                if (selectionKey.isAcceptable()) {
                    acceptHandler(serverSocketChannel, selector);
                }

                /**
                 * 如果是 可读事件
                 */
                if (selectionKey.isReadable()) {
                    readHandler(selectionKey, selector);
                }
            }
        }
    }

    /**
     * 接入事件处理器
     */
    private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector)
            throws IOException {
        /**
         * 如果要是接入事件，创建socketChannel
         */
        SocketChannel socketChannel = serverSocketChannel.accept();

        /**
         * 将socketChannel设置为非阻塞工作模式
         */
        socketChannel.configureBlocking(false);

        /**
         * 将channel注册到selector上，监听 可读事件
         */
        socketChannel.register(selector, SelectionKey.OP_READ);

        /**
         * 回复客户端提示信息
         */
        socketChannel.write(Charset.forName("UTF-8")
                .encode("你与聊天室里其他人都不是朋友关系，请注意隐私安全"));
    }

    /**
     * 可读事件处理器
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void readHandler(SelectionKey selectionKey, Selector selector)
            throws IOException {
        /**
         * 要从 selectionKey 中获取到已经就绪的channel
         */
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        /**
         * 创建buffer
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        /**
         * 循环读取客户端请求信息
         */
        String request = "";
        while (socketChannel.read(byteBuffer) > 0) {
            /**
             * 切换buffer为读模式
             */
            byteBuffer.flip();

            /**
             * 读取buffer中的内容
             */
            request += Charset.forName("UTF-8").decode(byteBuffer);
        }

        /**
         * 将channel再次注册到selector上，监听他的可读事件
         */
        socketChannel.register(selector, SelectionKey.OP_READ);

        /**
         * 将客户端发送的请求信息 广播给其他客户端
         */
        if (request.length() > 0) {
            // 广播给其他客户端
            broadCast(selector, socketChannel, request);
        }
    }

    /**
     * 广播给其他客户端
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void broadCast(Selector selector, SocketChannel sourceChannel, String request) {
        /**
         * 获取到所有已接入的客户端channel
         */
        Set<SelectionKey> selectionKeySet = selector.keys();

        /**
         * 循环向所有channel广播信息
         */
        selectionKeySet.forEach(new Consumer<SelectionKey>() {
            @Override
            public void accept(SelectionKey selectionKey) {
                Channel targetChannel = selectionKey.channel();

                // 剔除发消息的客户端
                if (targetChannel instanceof SocketChannel
                        && targetChannel != sourceChannel) {
                    try {
                        // 将信息发送到targetChannel客户端
                        ((SocketChannel) targetChannel).write(
                                Charset.forName("UTF-8").encode(request));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
