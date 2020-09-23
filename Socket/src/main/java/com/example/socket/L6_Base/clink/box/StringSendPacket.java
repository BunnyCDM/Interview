package com.example.socket.L6_Base.clink.box;

import com.example.socket.L6_Base.clink.core.SendPacket;

import java.io.IOException;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * 字符串发送包
 */
public class StringSendPacket extends SendPacket {

    private final byte[] bytes;

    public StringSendPacket(String msg) {
        this.bytes = msg.getBytes();
        this.length=bytes.length;
    }

    @Override
    public byte[] bytes() {
        return bytes;
    }


    @Override
    public void close() throws IOException {

    }
}
