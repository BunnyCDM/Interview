package com.example.socket.L6_Base.client;

/**
 * Created by mac on 2020-09-22.
 * <p>
 * 验证continue、break的使用
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        int count = 1;

        do {
            System.out.println("入口1 " + count);

            count = ++count;
            Thread.sleep(1000);
            if (count > 10 && count < 20) {
                System.out.println("入口======");
                //continue;//不跳出循环，不执行下面的操作
                break;//直接跳出循环
            }

            System.out.println("入口2");
            Thread.sleep(1000);

        } while (true);

    }
}
