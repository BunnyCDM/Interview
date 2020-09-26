package com.example.socket.L6_Base.clink.core;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by mac on 2020-09-23.
 */
public interface Sender extends Closeable {
    void setSenderListener(IoArgs.IoArgsEventProcessor processor);

    boolean postSendAsync() throws IOException;
}
