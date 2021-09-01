package com.example.aidl_client;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.aidl_service.IService;
import com.example.baselibrary.utils.log.AppLogger;

import org.joor.Reflect;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    IService RemoteService;//监听服务

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            AppLogger.d("onServiceConnected: service=" + service);
            RemoteService = IService.Stub.asInterface(service);

            try {
                String s = RemoteService.hello("finch");
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            AppLogger.d("onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();
    }

    private void initService() {
//        Intent i = new Intent();
//        i.setAction("android.intent.action.AIDLService");
//        i.setPackage("com.example.aidl_service");
//        boolean ret = bindService(i, mConnection, Context.BIND_AUTO_CREATE);
//        AppLogger.d("initService: ret=" + ret);

//        final Intent intent = new Intent();
//        intent.setAction("android.intent.action.AIDLService");
//        final Intent eintent = new Intent(createExplicitFromImplicitIntent(this, intent));
//        boolean ret = bindService(eintent, mConnection, Service.BIND_AUTO_CREATE);
//        AppLogger.d("initService: ret=" + ret);

        Intent intent = new Intent();
        intent.setAction("android.intent.action.AIDLService");
        intent.setPackage(getPackageName());
        Reflect.on(this).call("bindServiceAsUser", intent, mConnection, Context.BIND_AUTO_CREATE,
                Reflect.onClass(UserHandle.class).field("CURRENT").get());

//        Reflect.on(this).call("bindServiceAsUser",
//                new Intent(this, UNWebRTCService.class), mConnection, Context.BIND_AUTO_CREATE,
//                Reflect.onClass(UserHandle.class).field("CURRENT").get());
    }

    private void releaseService() {
        unbindService(mConnection);
        mConnection = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }


}
