package com.example.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.dagger.data.User;

import javax.inject.Inject;

/**
 * Created by mac on 2017/12/7.
 */

public class AActivity extends AppCompatActivity {

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppLogger.e("onCreate: user（1）=" + user);
        MyApplication.getComponent().inject(this);

        AppLogger.e("onCreate: user（2）=" + user);
        AppLogger.e("onCreate: " + user.getName());
    }

}
