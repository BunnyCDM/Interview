package com.example.socket.L6_Base.clink.core;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * 公共的数据封装
 * 提供了类型以及基本的长度的定义
 */
public abstract class Packet implements Closeable {

    protected byte type;
    protected int length;

    public byte type() {
        return type;
    }


    public int length() {
        return length;
    }

}
