package com.example.socket.L7_7_8_9_10.clink.core;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by mac on 2019-08-04.
 */
public interface Sender extends Closeable {
    boolean sendAsync(IoArgs args, IoArgs.IoArgsEventListener listener) throws IOException;
}
