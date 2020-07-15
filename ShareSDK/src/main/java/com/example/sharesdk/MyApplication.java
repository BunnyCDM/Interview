package com.example.sharesdk;

import android.app.Application;

import com.example.baselibrary.utils.log.AppLogger;
import com.mob.MobSDK;
import com.mob.OperationCallback;

/**
 * Created by mac on 2020-05-25.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        MobSDK.submitPolicyGrantResult(true, new OperationCallback<Void>() {
            @Override
            public void onComplete(Void aVoid) {
                AppLogger.d("onComplete: ");
            }

            @Override
            public void onFailure(Throwable throwable) {
                AppLogger.d("onFailure: " + throwable.getMessage());
            }
        });
    }
}
