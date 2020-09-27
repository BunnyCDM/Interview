package com.example.socket.L6_Base.clink.core;

import java.io.Closeable;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * 接收的数据调度封装
 * 把一份或者多分IoArgs组合成一份Packet
 */
public interface ReceiveDispatcher extends Closeable {

    void start();

    void stop();

    interface ReceivePacketCallback {

        ReceivePacket<?, ?> onArrivedNewPacket(byte type, long length);

        void onReceivePacketCompleted(ReceivePacket packet);

    }

}
