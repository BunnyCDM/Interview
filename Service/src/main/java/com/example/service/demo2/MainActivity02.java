package com.example.service.demo2;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.service.R;

/**
 * Created by mac on 2020-06-22.
 * <p>
 * service通信
 */
public class MainActivity02 extends AppCompatActivity {


    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);

        EditText editText = findViewById(R.id.mEditText);
        textView = findViewById(R.id.mTextView);

        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity02.this, MyService02.class);
                intent.putExtra("data", editText.getText().toString().trim());
                startService(intent);
            }
        });

        findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity02.this, MyService02.class));
            }
        });

        findViewById(R.id.btnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(MainActivity02.this, MyService02.class), serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btnUnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });


        findViewById(R.id.btnSysData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder != null) {
                    binder.setData(editText.getText().toString().trim());
                }
            }
        });

    }

    private MyService02.Binder binder;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AppLogger.d("onServiceConnected: ");
            binder = (MyService02.Binder) service;

            binder.getService().setCallBack(new MyService02.CallBack() {
                @Override
                public void onDataChanged(String data) {
                    AppLogger.d("onServiceConnected: data is " + data);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("data", data);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            AppLogger.d("onServiceDisconnected: ");

        }
    };

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AppLogger.d("handler: data is " + msg.getData().getString("data"));
            textView.setText(msg.getData().getString("data"));
        }
    };
}
