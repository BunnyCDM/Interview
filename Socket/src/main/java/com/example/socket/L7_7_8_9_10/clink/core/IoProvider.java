package com.example.socket.L7_7_8_9_10.clink.core;

/**
 * Created by mac on 2019-08-04.
 */

import java.io.Closeable;
import java.nio.channels.SocketChannel;

/**
 * 一个提供者模式
 */
public interface IoProvider extends Closeable {
    boolean registerInput(SocketChannel channel, HandleInputCallback callback);

    boolean registerOutput(SocketChannel channel, HandleOutputCallback callback);

    void unRegisterInput(SocketChannel channel);

    void unRegisterOutput(SocketChannel channel);

    abstract class HandleInputCallback implements Runnable {
        @Override
        public final void run() {
            canProviderInput();
        }

        protected abstract void canProviderInput();
    }

    abstract class HandleOutputCallback implements Runnable {
        private Object attach;

        @Override
        public final void run() {
            canProviderOutput(attach);
        }

        public final void setAttach(Object attach) {
            this.attach = attach;
        }

        protected abstract void canProviderOutput(Object attach);
    }

}
