package com.example.socket.L6_Base.clink.frames;

import com.example.socket.L6_Base.clink.core.Frame;
import com.example.socket.L6_Base.clink.core.IoArgs;

import java.io.IOException;

/**
 * Created by mac on 2020-09-28.
 */
public abstract class AbsReceiveFrame extends Frame {
    //桢体可读写区域大小
    volatile int bodyRemaining;

    AbsReceiveFrame(byte[] header) {
        super(header);
        bodyRemaining = getBodyLength();
    }

    @Override
    public synchronized boolean handle(IoArgs args) throws IOException {
        if (bodyRemaining == 0) {
            // 已读取所有数据
            return true;
        }

        bodyRemaining -= consumeBody(args);

        return bodyRemaining == 0;
    }


    @Override
    public Frame nextFrame() {
        return null;
    }


    @Override
    public int getConsumableLength() {
        return bodyRemaining;
    }

    protected abstract int consumeBody(IoArgs args) throws IOException;
}
