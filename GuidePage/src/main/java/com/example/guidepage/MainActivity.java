package com.example.guidepage;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.guidepage.delegates.LatteDelegate;
import com.example.guidepage.launcher.ILauncherListener;
import com.example.guidepage.launcher.LauncherDelegate;
import com.example.guidepage.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements ILauncherListener {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.delegate_launcher);
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Log.d("LauncherDelegate", "启动结束，用户登录了");
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                //getSupportDelegate().start(new EcBottomDelegate());
                break;

            case NOT_SIGNED:
                Log.d("LauncherDelegate", "启动结束，用户没登录");
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                //getSupportDelegate().start(new SignInDelegate());
                break;

            default:
                break;
        }
    }
}
