package com.mac.annotations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//    @Description(desc = "I am eyeColor", author = "Mooc boy", age = 18)
    @Description("I am eyeColor")
    //@Description
    public String eyeColor() {
        return "red";
    }


    //@SuppressWarnings("deprecation")//忽略警告
    @SuppressWarnings("ALL")//忽略警告
    public void sing(){
        Person person=new Child();
        person.sing();

    }

}