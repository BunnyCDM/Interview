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
 * <p>
 * //stream = new ByteArrayInputStream(bytes);
 */
public class StringSendPacket extends BytesSendPacket {

    public StringSendPacket(String msg) {
        super(msg.getBytes());
    }


    @Override
    public byte type() {
        return TYPE_MEMORY_STRING;
    }

}
