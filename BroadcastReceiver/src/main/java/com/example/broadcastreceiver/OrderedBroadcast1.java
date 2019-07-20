package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class OrderedBroadcast1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("tag", "OrderedBroadcast1收到信息");
        Bundle bundle = intent.getExtras();
        Log.d("tag", bundle.getString("msg"));
        abortBroadcast();//终止广播
    }
}

