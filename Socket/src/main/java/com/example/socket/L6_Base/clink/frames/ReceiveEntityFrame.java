package com.example.socket.L6_Base.clink.frames;

import com.example.socket.L6_Base.clink.core.IoArgs;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

/**
 * Created by mac on 2020-09-28.
 */
public class ReceiveEntityFrame extends AbsReceiveFrame{
    private WritableByteChannel channel;

    public ReceiveEntityFrame(byte[] header) {
        super(header);
    }


    public void bindPacketChannel(WritableByteChannel channel){
        this.channel=channel;
    }

    @Override
    protected int consumeBody(IoArgs args) throws IOException {
        return channel == null ? args.setEmpty(bodyRemaining) : args.writeTo(channel);
    }
}
