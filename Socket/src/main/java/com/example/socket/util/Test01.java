package com.example.socket.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by mac on 2019/3/16.
 * <p>
 * InetAddress类
 */

public class Test01 {

    public static void main(String[] args) throws UnknownHostException {

        // 获取本机的InetAddress实例
        InetAddress address = InetAddress.getLocalHost();
        InetAddress addressv4 = Inet4Address.getLocalHost();

        System.out.println("计算机名称：" + address.getHostName());
        System.out.println("计算机名称v4：" + addressv4.getHostName());

        System.out.println("IP地址：" + address.getHostAddress());
        byte[] bytes = address.getAddress();//获取字节数组形式的IP地址
        System.out.println("字节数组形式的IP：" + Arrays.toString(bytes));
        System.out.println("address：" + address);//直接输出InetAddress对象

        //根据机器名获取InetAddress实例
        //InetAddress address2=InetAddress.getByName();
        //InetAddress address2=InetAddress.getByName("1.1.1.10");
        InetAddress address2 = InetAddress.getByName("192.168.0.102");
        System.out.println("计算名：" + address2.getHostName());
        System.out.println("IP地址：" + address2.getHostAddress());


    }

}
