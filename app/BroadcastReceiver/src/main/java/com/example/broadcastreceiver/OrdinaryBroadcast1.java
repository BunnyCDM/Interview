package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class OrdinaryBroadcast1 extends BroadcastReceiver {
    Context mContext;
    OrdinaryBroadcast1 mOrdinaryBroadcast1;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("tag", "OrdinaryBroadcast1收到广播");
        Log.d("tag", intent.getStringExtra("msg"));
    }

    public OrdinaryBroadcast1(Context context) {
        mContext = context;
        mOrdinaryBroadcast1 = this;
    }

    public void registerAction(String action) {
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(action);
        mContext.registerReceiver(mOrdinaryBroadcast1, mIntentFilter);
    }

}
