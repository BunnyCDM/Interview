package com.example.socket.L5_TCP_Channel.clink.net.qiujuer.clink.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by mac on 2019/7/14.
 */
public class CloseUtils {

    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

