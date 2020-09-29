package com.example.socket.L6_Base.clink.frames;

import com.example.socket.L6_Base.clink.core.IoArgs;

import java.io.IOException;

/**
 * Created by mac on 2020-09-29.
 * <p>
 * 取消传输帧，接收实现
 */
public class CancelReceiveFrame extends AbsReceiveFrame{

    public CancelReceiveFrame(byte[] header) {
        super(header);
    }

    @Override
    protected int consumeBody(IoArgs args) throws IOException {
        return 0;
    }
}
