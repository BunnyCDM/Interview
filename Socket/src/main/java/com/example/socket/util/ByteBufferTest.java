package com.example.socket.util;

import java.nio.ByteBuffer;

/**
 * Created by mac on 2019-08-03.
 * <p>
 * ByteBuffer属性描述如下：
 * Capacity	容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变
 * Limit	表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的
 * Position	位置，下一个要被读或写的元素的索引，每次读写缓冲区数据时都会改变改值，为下次读写作准备
 * Mark	标记，调用mark()来设置mark=position，再调用reset()可以让position恢复到标记的位置
 */
public class ByteBufferTest {

    public static void main(String args[]) {

        /**
         * freeMemory：在Java运行过程中内存总是慢慢从操作系统那里挖，挖过来多余没用的部分就是freeMemory
         * totalMemory：java虚拟机现在已经从操作系统那里挖过来的内存大小，也就是java虚拟机这个进程当时所占用的所有 内存
         * maxMemory：java虚拟机（这个进程）能构从操作系统那里挖到的最大的内存，以字节为单位
         * 以上反映都是java进程内存的情况，跟操作系统的内存根本没什么关系
         */
        System.out.println("before freeMemory:" + Runtime.getRuntime().freeMemory());
        System.out.println("before totalMemory:" + Runtime.getRuntime().totalMemory());
        System.out.println("before maxMemory:" + Runtime.getRuntime().maxMemory());
        //如果分配的内存比较小，调用Runtime.getRuntime().freeMemory()大小不会变化？
        //要超过多少内存大小JVM才能感觉到？
        ByteBuffer buffer = ByteBuffer.allocate(102400);//102400
        System.out.println("buffer=" + buffer);
        System.out.println("after alocate:" + Runtime.getRuntime().freeMemory());

        // 这部分直接用的系统内存，所以对JVM的内存没有影响
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);//102400
        System.out.println("\ndirectBuffer = " + directBuffer);
        System.out.println("after direct alocate:" + Runtime.getRuntime().freeMemory());


        System.out.println("\n\n----------Test wrap--------");
        byte[] bytes = new byte[128];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);
        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);
    }
}
