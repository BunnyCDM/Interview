package com.example.dagger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.dagger.data.User;

import javax.inject.Inject;


public class MainActivity extends Activity {

    private final static String TAG = "cdm";

    @Inject
    User user;//这里不能申请为private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "onCreate: user（1）=" + user);
        MyApplication.getComponent().inject(this);
        Log.e(TAG, "onCreate: user（2）=" + user);

        MyApplication.getComponent().provideUser();
        user.setName("chend");
        Log.d(TAG, "onCreate: " + user.getName());

    }


    public void btn_jump(View view) {
        startActivity(new Intent(this, AActivity.class));
    }


}
