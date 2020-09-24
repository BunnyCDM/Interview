package com.example.socket.L6_Base.clink.impl.async;

import android.widget.Toast;

import com.example.socket.L6_Base.clink.CloseUtils;
import com.example.socket.L6_Base.clink.box.StringReceivePacket;
import com.example.socket.L6_Base.clink.core.IoArgs;
import com.example.socket.L6_Base.clink.core.ReceiveDispatcher;
import com.example.socket.L6_Base.clink.core.ReceivePacket;
import com.example.socket.L6_Base.clink.core.Receiver;
import com.example.socket.L6_Base.clink.core.SendPacket;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mac on 2020-09-23.
 */
public class AsyncReceiveDispatcher implements ReceiveDispatcher {

    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    private final Receiver receiver;
    private final ReceivePacketCallback callback;

    private IoArgs ioArgs = new IoArgs();
    private ReceivePacket packetTemp;
    private byte[] buffer;

    //当前发送的packet大小，以及进度
    private int total;
    private int position;

    public AsyncReceiveDispatcher(Receiver receiver, ReceivePacketCallback callback) {
        this.receiver = receiver;
        this.receiver.setReceiveListener(ioArgsEventListener);
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

    /**
     * 注册接收数据
     */
    private void registerReceive() {
        try {
            receiver.receiveAsync(ioArgs);
        } catch (Exception e) {
            closeAndNotify();
        }
    }

    @Override
    public void close() throws IOException {
        if (isClosed.compareAndSet(false, true)) {
            ReceivePacket packet=packetTemp;
            if(packet!=null){
                packetTemp=null;
                CloseUtils.close(packet);
            }
        }
    }

    private void closeAndNotify() {
        CloseUtils.close(this);
    }

    private final IoArgs.IoArgsEventListener ioArgsEventListener = new IoArgs.IoArgsEventListener() {
        @Override
        public void onStarted(IoArgs args) {

            int receiveSize;
            if (packetTemp == null) {
                receiveSize = 4;
            } else {
                receiveSize = Math.min(total - position, args.capactity());
            }

            //设置本次接收数据大小
            args.limit(receiveSize);
        }

        @Override
        public void onCompleted(IoArgs args) {
            assemblePacket(args);

            //继续接收下一条数据
            registerReceive();
        }
    };

    /**
     * 解析数据到Packet
     */
    private void assemblePacket(IoArgs args) {
        if (packetTemp == null) {
            int length = args.readLength();
            packetTemp = new StringReceivePacket(length);
            buffer = new byte[length];
            total = length;
            position = 0;
        }

        int count = args.writeTo(buffer, 0);
        if (count > 0) {
            packetTemp.save(buffer, count);
            position += count;

            if (position == total) {
                completePacket();
                packetTemp = null;
            }
        }
    }

    /**
     * 完成数据接收操作
     */
    private void completePacket() {
        ReceivePacket packet = this.packetTemp;
        CloseUtils.close(packet);
        callback.onReceivePacketCompleted(packet);
    }
}
