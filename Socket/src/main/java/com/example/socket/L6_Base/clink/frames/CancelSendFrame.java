package com.example.socket.L6_Base.clink.frames;

import com.example.socket.L6_Base.clink.core.Frame;
import com.example.socket.L6_Base.clink.core.IoArgs;

import java.io.IOException;

/**
 * Created by mac on 2020-09-28.
 * <p>
 * 取消发送帧，用于标志某Packet取消进行发送数据
 */
public class CancelSendFrame extends AbsSendFrame{

    public CancelSendFrame(short identifier) {
        super(0, TYPE_COMMAND_SEND_CANCEL, Frame.FLAG_NONE, identifier);
    }

    @Override
    protected int consumeBody(IoArgs args) throws IOException {
        return 0;
    }

    @Override
    public Frame nextFrame() {
        return null;
    }
}
