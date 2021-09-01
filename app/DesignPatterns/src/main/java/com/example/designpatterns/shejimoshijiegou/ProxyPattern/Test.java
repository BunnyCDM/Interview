package com.example.designpatterns.shejimoshijiegou.ProxyPattern;

/**
 * Created by mac on 2019/1/21.
 */

public class Test {

    public static void main(String args[]) {
        people people_1 = new people();
        people_1.setCash(60000);
        people_1.setUsername("jeck");


        people people_2 = new people();
        people_2.setCash(40000);
        people_2.setUsername("rose");

        people people_3 = new people();

        people_3.setCash(0);
        people_3.setUsername("tom");
        people_3.setVip("vip");

        proxyclass proxy_buy = new proxyclass();
        proxy_buy.setPeople(people_1);
        proxy_buy.buy_mycar();

        proxy_buy.setPeople(people_2);
        proxy_buy.buy_mycar();

        proxy_buy.setPeople(people_3);
        proxy_buy.buy_mycar();

    }

}
