package com.example.socket.L6_4_5.clink.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by mac on 2019-08-04.
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

