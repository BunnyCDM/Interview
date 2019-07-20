package com.example.sign;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import com.example.sign.delegate.LatteDelegate;
import com.example.sign.sign.ISignListener;
import com.example.sign.sign.SignInDelegate;
import com.example.sign.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ISignListener {

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignInDelegate();
        //return new SignUpDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //setContentView(R.layout.delegate_sign_in);
    }

    @Override
    public void onSignInSuccess() {
        Log.d("MainActivity", "登录成功");
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Log.d("MainActivity", "注册成功");
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }
}
