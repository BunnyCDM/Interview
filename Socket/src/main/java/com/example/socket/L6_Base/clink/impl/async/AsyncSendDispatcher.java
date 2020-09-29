package com.example.socket.L6_Base.clink.impl.async;

import com.example.socket.L6_Base.clink.CloseUtils;
import com.example.socket.L6_Base.clink.core.IoArgs;
import com.example.socket.L6_Base.clink.core.SendDispatcher;
import com.example.socket.L6_Base.clink.core.SendPacket;
import com.example.socket.L6_Base.clink.core.Sender;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mac on 2020-09-23.
 */
public class AsyncSendDispatcher implements SendDispatcher, IoArgs.IoArgsEventProcessor , AsyncPacketReader.PacketProvider {

    private final Sender sender;
    private final Queue<SendPacket> queue = new ConcurrentLinkedDeque<>();
    private final AtomicBoolean isSending = new AtomicBoolean();
    private final AtomicBoolean isClosed = new AtomicBoolean(false);

    private final AsyncPacketReader reader = new AsyncPacketReader(this);
    private final Object queueLock = new Object();

    public AsyncSendDispatcher(Sender sender) {
        this.sender = sender;
        sender.setSenderListener(this);
    }

    @Override
    public void send(SendPacket packet) {
        synchronized (queueLock) {
            queue.offer(packet);
            if (isSending.compareAndSet(false, true)) {
                if (reader.requestTakePacket()) {
                    requestSend();
                }
            }
        }
    }

    /**
     * 取消Packet操作
     * 如果还在队列中，代表Packet未进行发送，则直接标志取消，并返回即可
     * 如果未在队列中，则让reader尝试扫描当前发送序列，查询是否当前Packet正在发送
     * 如果是则进行取消相关操作
     *
     * @param packet 数据
     */
    @Override
    public void cancel(SendPacket packet) {
        boolean ret;
        synchronized (queueLock) {
            ret = queue.remove(packet);
        }
        if (ret) {
            packet.cancel();
            return;
        }

        reader.cancel(packet);
    }

    /**
     * reader从当前队列中提取一份Packet
     *
     * @return 如果队列有可用于发送的数据则返回该Packet
     */
    @Override
    public SendPacket takePacket() {
        SendPacket packet;
        synchronized (queueLock) {
            packet = queue.poll();
            if (packet == null) {
                // 队列为空，取消发送状态
                isSending.set(false);
                return null;
            }
        }

        if (packet.isCanceled()) {
            // 已取消，不用发送
            return takePacket();
        }
        return packet;
    }

    /**
     * 完成Packet发送
     *
     * @param isSucceed 是否成功
     */
    @Override
    public void completedPacket(SendPacket packet, boolean isSucceed) {
        CloseUtils.close(packet);
    }

    /**
     * 请求网络进行数据发送
     */
    private void requestSend() {
        try {
            sender.postSendAsync();
        } catch (IOException e) {
            closeAndNotify();
        }
    }

    private void closeAndNotify() {
        CloseUtils.close(this);
    }

    /**
     * 关闭操作，关闭自己同时需要关闭reader
     *
     * @throws IOException 异常
     */
    @Override
    public void close() throws IOException {
        if (isClosed.compareAndSet(false, true)) {
            isSending.set(false);
            // reader关闭
            reader.close();
        }
    }

    /**
     * 网络发送就绪回调，当前已进入发送就绪状态，等待填充数据进行发送
     * 此时从reader中填充数据，并进行后续网络发送
     *
     * @return NULL，可能填充异常，或者想要取消本次发送
     */
    @Override
    public IoArgs provideIoArgs() {
        return reader.fillData();
    }


    /**
     * 网络发送IoArgs出现异常
     *
     * @param args IoArgs
     * @param e    异常信息
     */
    @Override
    public void onConsumeFailed(IoArgs args, Exception e) {
        if (args != null) {
            e.printStackTrace();
        } else {
            // TODO
        }
    }

    /**
     * 网络发送IoArgs完成回调
     * 在该方法进行reader对当前队列的Packet提取，并进行后续的数据发送注册
     *
     * @param args IoArgs
     */
    @Override
    public void onConsumeCompleted(IoArgs args) {
        // 继续发送当前包
        if (reader.requestTakePacket()) {
            requestSend();
        }
    }


}
