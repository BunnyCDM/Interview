package com.example.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    Button mButtonOrdinary, mButtonOrdered;
    MyClickListener mMyClickListener;

    OrdinaryBroadcast1 mOrdinaryBroadcast1;
    OrdinaryBroadcast2 mOrdinaryBroadcast2;
    BroadcastReceiver ordinaryBroadcast3 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("tag", "OrdinaryBroadcast3收到广播");
            Log.d("tag", intent.getStringExtra("msg"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();

    }

    private void initView() {
        mButtonOrdinary = (Button) findViewById(R.id.btn_Ordinary);
        mButtonOrdered = (Button) findViewById(R.id.btn_Ordered);
    }

    private void initData() {

        IntentFilter filter2 = new IntentFilter("com.asw.broadcast1");
        mOrdinaryBroadcast2 = new OrdinaryBroadcast2();
        registerReceiver(mOrdinaryBroadcast2, filter2);

        IntentFilter filter3 = new IntentFilter();
        filter3.addAction("com.asw.broadcast1");
        registerReceiver(ordinaryBroadcast3, filter3);

        mMyClickListener = new MyClickListener();
    }

    private void initEvent() {
        mButtonOrdinary.setOnClickListener(mMyClickListener);
        mButtonOrdered.setOnClickListener(mMyClickListener);
    }

    public class MyClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_Ordinary://无法终止广播，即无法阻止其他接收者的接收动作
                    Intent intent1 = new Intent();
                    intent1.setAction("com.asw.broadcast1");
                    intent1.putExtra("msg", "普通广播发送信息");
                    sendBroadcast(intent1);
                    break;
                case R.id.btn_Ordered:
                    Intent intent2 = new Intent();
                    intent2.setAction("com.asw.broadcast2");
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "有序广播发送信息");
                    intent2.putExtras(bundle);
                    //sendOrderedBroadcast(intent2, null);//第二个参数指声明指定的权限
                    sendOrderedBroadcast(intent2, "asw.permission.MY_BROADCAST_PERMISSION");
                    break;
            }
        }

    }

    @Override
    protected void onStart() {//onStart中注册
        super.onStart();
        mOrdinaryBroadcast1 = new OrdinaryBroadcast1(this);
        mOrdinaryBroadcast1.registerAction("com.asw.broadcast1");
    }

    @Override
    protected void onStop() {//onStop中卸载
        super.onStop();
        unregisterReceiver(mOrdinaryBroadcast1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mOrdinaryBroadcast2);
        unregisterReceiver(ordinaryBroadcast3);
    }

}
