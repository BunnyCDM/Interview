package com.example.permissions;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity2 extends BaseActivity {

    private static final String TAG = "bunny";
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static final int REQUEST_OPEN_BLUETOOTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
    }

    private void requestPermission() {
        requestPermission(new String[]{Manifest.permission.BLUETOOTH_ADMIN
                        , Manifest.permission.ACCESS_COARSE_LOCATION}
                , "请求蓝牙相关权限"
                , new GrantedResult() {
                    @Override
                    public void onResult(boolean granted) {
                        Log.d(TAG, "onResult: granted=" + granted);
                        if (granted) {
                            checkBluetooth();
                        } else {
                            finish();
                        }
                    }
                });
    }


    private void checkBluetooth() {
        boolean isEnabled = BluetoothAdapter.getDefaultAdapter().isEnabled();
        if (!isEnabled) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_OPEN_BLUETOOTH);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + " resultCode=" + resultCode);
        if (requestCode == REQUEST_OPEN_BLUETOOTH && resultCode == RESULT_CANCELED) {
            finish();
        } else if (requestCode == REQUEST_OPEN_BLUETOOTH && resultCode == RESULT_OK) {
            //若打开，则进行扫描
        }
    }
}
