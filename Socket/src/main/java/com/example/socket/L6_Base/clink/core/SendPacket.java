package com.example.socket.L6_Base.clink.core;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * 发送的包定义
 */
public abstract class SendPacket extends Packet {

    private boolean isCanceled;

    public abstract byte[] bytes();

    public boolean isCanceled() {
        return isCanceled;
    }


}
