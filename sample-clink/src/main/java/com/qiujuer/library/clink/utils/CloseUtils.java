package com.qiujuer.library.clink.utils;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {

    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable == null) {
                continue;
            }
            try {
                /**
                 * 在java.io.包下 InputStream，outputStream, Reader, Writer 等基类都实现了Closeable接口，因为每次的IO操作结束之后都要去释放资源
                 * close()方法是关闭流并且释放与其相关的任何方法，如果流已被关闭，那么调用此方法没有效果
                 */
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

