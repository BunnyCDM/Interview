package com.example.service.demo1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.service.R;

/**
 * 启动service和停止service、绑定service和解绑service、生命周期
 */
public class MainActivity01 extends AppCompatActivity implements ServiceConnection {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);

        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity01.this, MyService01.class));
            }
        });

        findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity01.this, MyService01.class));
            }
        });

        findViewById(R.id.btnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                bindService(new Intent(MainActivity01.this, MyService01.class), MainActivity01.this, Context.BIND_AUTO_CREATE);


                if (!isBindService) {
                    isBindService = true;
                    bindService(new Intent(MainActivity01.this, MyService01.class), serviceConnection, Context.BIND_AUTO_CREATE);

                }
            }
        });

        findViewById(R.id.btnUnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                unbindService(MainActivity01.this);

                if (!isBindService) {
                    isBindService = false;
                    unbindService(serviceConnection);
                }
            }
        });
    }

    boolean isBindService = false;

    private MyService01.MusicBind musicBind;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBind = (MyService01.MusicBind) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        AppLogger.d("onServiceConnected: ");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        AppLogger.d("onServiceDisconnected: ");
    }
}
