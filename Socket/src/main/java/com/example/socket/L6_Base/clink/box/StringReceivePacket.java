package com.example.socket.L6_Base.clink.box;

import com.example.socket.L6_Base.clink.core.ReceivePacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mac on 2020-09-23.
 */
public class StringReceivePacket extends AbsByteArrayReceivePacket<String> {


    public StringReceivePacket(long len) {
        super(len);
    }

    @Override
    protected String buildEntity(ByteArrayOutputStream stream) {
        return new String(stream.toByteArray());
    }


    @Override
    public byte type() {
        return TYPE_MEMORY_STRING;
    }
}
