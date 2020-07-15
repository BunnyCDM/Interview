package com.example.intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity03 extends AppCompatActivity {

    public static final String ACTION = "com.example.intent.intent.action.MainActivity02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main03);
    }
}
