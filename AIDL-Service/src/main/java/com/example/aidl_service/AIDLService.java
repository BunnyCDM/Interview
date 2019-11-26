package com.example.aidl_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by mac on 2019-11-26.
 */
public class AIDLService extends Service {

    private static final String TAG = AIDLService.class.getSimpleName();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IService.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                Log.d(TAG, "basicTypes: ");
            }

            @Override
            public String hello(String name) throws RemoteException {
                Log.d(TAG, "hello: " + name);
                return "hello" + name;
            }
        };
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }
}
