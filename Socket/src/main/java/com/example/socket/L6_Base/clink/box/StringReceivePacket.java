package com.example.socket.L6_Base.clink.box;

import com.example.socket.L6_Base.clink.core.ReceivePacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by mac on 2020-09-23.
 */
public class StringReceivePacket extends ReceivePacket<ByteArrayOutputStream> {

    private String string;

    public StringReceivePacket(int len) {
        length = len;
    }

    public String string() {
        return string;
    }



    @Override
    protected ByteArrayOutputStream createStream() {
        return new ByteArrayOutputStream((int) length);
    }

    @Override
    protected void closeStream(ByteArrayOutputStream stream) throws IOException {
        super.closeStream(stream);
        string=new String(stream.toByteArray());
    }
}
