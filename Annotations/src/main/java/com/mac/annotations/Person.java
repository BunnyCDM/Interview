package com.mac.annotations;


@Description("I am interface class")
public class Person {


    @Description("I am interface method")
    public String name(){
        return null;
    }

    public int age(){
        return 0;
    }


    /**
     * 该方法已经过时，建议不要在使用
     */
    @Deprecated
    public void sing(){
    }
}
