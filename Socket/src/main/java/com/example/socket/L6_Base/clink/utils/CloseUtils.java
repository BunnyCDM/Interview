package com.example.socket.L6_Base.clink.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by mac on 2020-09-21.
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
