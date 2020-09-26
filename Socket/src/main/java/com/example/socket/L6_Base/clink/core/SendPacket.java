package com.example.socket.L6_Base.clink.core;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * 发送的包定义
 */
public abstract class SendPacket<T extends InputStream> extends Packet<T> {

    private boolean isCanceled;

    public boolean isCanceled() {
        return isCanceled;
    }





}
