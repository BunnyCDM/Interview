package com.example.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


import com.example.dagger.data.User;

import javax.inject.Inject;

/**
 * Created by mac on 2017/12/7.
 */

public class AActivity extends Activity {

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getComponent().inject(this);

        Log.e("cdm", "onCreate: user=" + user);
        Log.d("cdm", "onCreate: " + user.getName());
    }

}
