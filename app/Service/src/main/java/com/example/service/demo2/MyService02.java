package com.example.service.demo2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.baselibrary.utils.log.AppLogger;

public class MyService02 extends Service {

    private boolean isServiceRunning = false;
    private String data = "这是默认信息";

    public MyService02() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();
    }

    public class Binder extends android.os.Binder {

        public void setData(String data){
            MyService02.this.data=data;
        }

        public MyService02 getService(){
            return MyService02.this;
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

                int i=0;

                while (isServiceRunning) {
                    try {
                        i++;
                        AppLogger.d(data+",i is "+i);
                        if(callBack!=null){
                            callBack.onDataChanged(data+",i is "+i);
                        }
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

        data=intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        AppLogger.d("onDestroy: ");
        isServiceRunning = false;
    }

    private CallBack callBack=null;

    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public static interface  CallBack{
        void onDataChanged(String data);
    }


}
