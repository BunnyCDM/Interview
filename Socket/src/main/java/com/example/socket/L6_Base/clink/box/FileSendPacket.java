package com.example.socket.L6_Base.clink.box;

import com.example.socket.L6_Base.clink.core.SendPacket;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * 字符串发送包
 *
 * //stream = new FileInputStream(bytes);
 */
public class FileSendPacket extends SendPacket<FileInputStream> {


    public FileSendPacket(File file) {
        this.length = file.length();
    }


    @Override
    protected FileInputStream createStream() {
        return null;
    }

}
