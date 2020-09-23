package com.example.socket.L6_Base.clink.core;

/**
 * Created by mac on 2020-09-23.
 * <p>
 * 接收包的定义
 */
public abstract class ReceivePacket extends Packet{
    public abstract void save(byte[] bytes,int count);
}
