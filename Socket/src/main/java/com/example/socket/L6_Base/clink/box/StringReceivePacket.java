package com.example.socket.L6_Base.clink.box;

import com.example.socket.L6_Base.clink.core.ReceivePacket;

import java.io.IOException;

/**
 * Created by mac on 2020-09-23.
 */
public class StringReceivePacket extends ReceivePacket {

    private byte[] buffer;
    private int position;

    public StringReceivePacket(int len) {
        this.buffer = new byte[len];
        length = len;
    }

    @Override
    public void save(byte[] bytes, int count) {
        System.arraycopy(bytes, 0, buffer, position, count);
        position += count;
    }

    public String string() {
        return new String(buffer);
    }

    @Override
    public void close() throws IOException {

    }
}
