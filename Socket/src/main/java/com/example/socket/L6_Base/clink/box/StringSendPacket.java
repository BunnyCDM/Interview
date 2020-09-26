package com.example.socket.L6_Base.clink.box;

import com.example.socket.L6_Base.clink.core.SendPacket;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * 字符串发送包
 *
 * //stream = new ByteArrayInputStream(bytes);
 */
public class StringSendPacket extends SendPacket<ByteArrayInputStream> {

    private final byte[] bytes;

    public StringSendPacket(String msg) {
        this.bytes = msg.getBytes();
        this.length = bytes.length;
    }


    @Override
    protected ByteArrayInputStream createStream() {
        return new ByteArrayInputStream(bytes);
    }


}
