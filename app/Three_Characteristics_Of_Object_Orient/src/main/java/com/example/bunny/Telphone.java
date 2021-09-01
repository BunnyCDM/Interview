package com.example.bunny;

public abstract class Telphone {

    public abstract void call();

    public abstract void message();


    // TODO: 2019-08-17 改方法不会强制让子类去实现，以上abstract void xx（）方法才会被强制引用哈
    public String getTest() {
        return "abstract class Telphone getTest() 非abstract标注";
    }

    // TODO: 2019-08-17 倘若Telphone implements IPlayGame以下方法可重写或不被重写，但是在最外非abstract类 一定会被重写否则会报错
//    @Override
//    public void playGame() {
//    }

}
