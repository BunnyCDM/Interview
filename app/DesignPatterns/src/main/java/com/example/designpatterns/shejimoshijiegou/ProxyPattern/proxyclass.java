package com.example.designpatterns.shejimoshijiegou.ProxyPattern;

/**
 * Created by mac on 2019/1/21.
 * <p>
 * people类不能拥有车，必须经过proxy代理类的认证，
 * 符合条件之后才可以拥有车辆，新建一个代理，这个代理类来考察当前的people是否有资格进行买车
 */

public class proxyclass implements buy_car {

    private people people;

    public people getPeople() {
        return people;
    }

    public void setPeople(people people) {
        this.people = people;
    }

    @Override
    public void buy_mycar() {

        if (people.getVip() == "vip") {
            people.buy_mycar();
            return;
        }
        if (people.getCash() >= 50000) {
            System.out.println(people.getUsername() + "买了新车，交易结束！");
        } else {
            System.out.println(people.getUsername() + "钱不够，不能买车，继续比赛！");
        }

    }

}
