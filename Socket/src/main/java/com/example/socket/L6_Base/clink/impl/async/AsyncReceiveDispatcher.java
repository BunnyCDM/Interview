package com.example.socket.L6_Base.clink.impl.async;

import com.example.socket.L6_Base.clink.CloseUtils;
import com.example.socket.L6_Base.clink.box.StringReceivePacket;
import com.example.socket.L6_Base.clink.core.IoArgs;
import com.example.socket.L6_Base.clink.core.Packet;
import com.example.socket.L6_Base.clink.core.ReceiveDispatcher;
import com.example.socket.L6_Base.clink.core.ReceivePacket;
import com.example.socket.L6_Base.clink.core.Receiver;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mac on 2020-09-23.
 */
public class AsyncReceiveDispatcher implements ReceiveDispatcher, IoArgs.IoArgsEventProcessor, AsyncPacketWriter.PacketProvider {

    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    private final Receiver receiver;
    private final ReceivePacketCallback callback;

    private AsyncPacketWriter writer = new AsyncPacketWriter(this);

    public AsyncReceiveDispatcher(Receiver receiver, ReceivePacketCallback callback) {
        this.receiver = receiver;
        this.receiver.setReceiveListener(this);
        this.callback = callback;
    }


    /**
     * 开始进入接收方法
     */
    @Override
    public void start() {
        registerReceive();
    }

    /**
     * 停止接收数据
     */
    @Override
    public void stop() {
    }


    @Override
    public void close() throws IOException {
        if (isClosed.compareAndSet(false, true)) {
            writer.close();
        }
    }

    private void closeAndNotify() {
        CloseUtils.close(this);
    }

    /**
     * 注册接收数据
     */
    private void registerReceive() {
        try {
            receiver.postReceiveAsync();
        } catch (Exception e) {
            closeAndNotify();
        }
    }


    /**
     * 网络接收就绪，此时可以读取数据，需要返回一个容器用于容纳数据
     *
     * @return 用以容纳数据的IoArgs
     */
    @Override
    public IoArgs provideIoArgs() {
        return writer.takeIoArgs();
    }

    /**
     * 接收数据失败
     *
     * @param args IoArgs
     * @param e    异常信息
     */
    @Override
    public void onConsumeFailed(IoArgs args, Exception e) {
        e.printStackTrace();
    }

    /**
     * 接收数据成功
     *
     * @param args IoArgs
     */
    @Override
    public void onConsumeCompleted(IoArgs args) {
        // 有数据则重复消费
        do {
            writer.consumeIoArgs(args);
        } while (args.remained());
        registerReceive();
    }

    /**
     * 构建Packet操作，根据类型、长度构建一份用于接收数据的Packet
     */
    @Override
    public ReceivePacket takePacket(byte type, long length, byte[] headerInfo) {
        return callback.onArrivedNewPacket(type, length);
    }

    /**
     * 当Packet接收数据完成或终止时回调
     *
     * @param packet    接收包
     * @param isSucceed 是否成功接收完成
     */
    @Override
    public void completedPacket(ReceivePacket packet, boolean isSucceed) {
        CloseUtils.close(packet);
        callback.onReceivePacketCompleted(packet);
    }
}
