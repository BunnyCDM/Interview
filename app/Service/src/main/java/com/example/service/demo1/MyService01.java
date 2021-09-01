package com.example.service.demo1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.baselibrary.utils.log.AppLogger;

public class MyService01 extends Service {

    private boolean isServiceRunning = false;

    public MyService01() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();
    }

    public class MusicBind extends Binder {
        public void play() {

        }

        public void stop() {

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isServiceRunning = true;

        AppLogger.d("onCreate: ");
        new Thread() {
            @Override
            public void run() {
                super.run();

                while (isServiceRunning) {
                    try {
                        AppLogger.d("服务正在运行");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.d("onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        AppLogger.d("onDestroy: ");
        isServiceRunning = false;
    }


}
