package com.example.socket.L6_Base.clink.frames;

import com.example.socket.L6_Base.clink.core.IoArgs;

import java.io.IOException;

/**
 * Created by mac on 2020-09-30.
 * <p>
 * 心跳接收帧
 */
public class HeartbeatReceiveFrame extends AbsReceiveFrame {


    static final HeartbeatReceiveFrame INSTANCE = new HeartbeatReceiveFrame();

    private HeartbeatReceiveFrame() {
        super(HeartbeatSendFrame.HEARTBEAT_DATA);
    }

    @Override
    protected int consumeBody(IoArgs args) throws IOException {
        return 0;
    }
}
