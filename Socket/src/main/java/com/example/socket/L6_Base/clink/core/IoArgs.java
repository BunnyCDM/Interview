package com.example.socket.L6_Base.clink.core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * IO输入输出参数类
 */
public class IoArgs {

    private byte[] byteBuffer = new byte[256];
    /**
     * 单消息不完整测试
     */
//    private byte[] byteBuffer = new byte[4];
    private ByteBuffer buffer = ByteBuffer.wrap(byteBuffer);

    public int read(SocketChannel channel) throws IOException {
        buffer.clear();
        return channel.read(buffer);
    }


    public int write(SocketChannel channel) throws IOException {
        return channel.write(buffer);
    }

    public String bufferString() {
        // 丢弃换行符
        return new String(byteBuffer, 0, buffer.position() - 1);
    }

    public interface IoArgsEventListener {
        void onStarted(IoArgs args);

        void onCompleted(IoArgs args);
    }
}
