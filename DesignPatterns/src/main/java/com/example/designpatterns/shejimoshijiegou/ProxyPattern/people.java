package com.example.designpatterns.shejimoshijiegou.ProxyPattern;

/**
 * Created by mac on 2019/1/21.
 * <p>
 * 新建一个people人类，具有买车的行为，所以实现接口buy_car
 */

public class people implements buy_car {

    private int cash;
    private String vip;
    private String username;

    @Override
    public void buy_mycar() {
        System.out.print(username+"是vip 客户，可以直接购买新车！");
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
