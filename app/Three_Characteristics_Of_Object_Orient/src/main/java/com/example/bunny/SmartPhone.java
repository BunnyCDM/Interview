package com.example.bunny;

public class SmartPhone extends Telphone implements IPlayGame {

    @Override
    public void call() {
        System.out.println("通过语音打电话");
    }

    @Override
    public void message() {
        System.out.println("通过语音发短信");
    }

    @Override
    public String getTest() {
        return super.getTest();
    }

    @Override
    public void playGame() {
        System.out.println("具有了玩游戏的功能");
    }

}
