package com.example.fragment.demo2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.fragment.R;
import com.example.fragment.lifecycle.HotspotFragment;
import com.example.fragment.lifecycle.TopLineFragment;

/**
 * Created by mac on 2019/2/26.
 * <p>
 * 演示Activity向Fragment传值
 */

public class Demo2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_content;
    private Button btn_pass;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pass:
                String info = et_content.getText().toString();
                ResultFragment resultFragment = new ResultFragment();
                Bundle bundle = new Bundle();
                bundle.putString("info", info);
                resultFragment.setArguments(bundle);

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.contentlayout, resultFragment);
                transaction.commit();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        AppLogger.d("------Demo2Activity------onCreate: ");
        et_content = findViewById(R.id.et_content);
        btn_pass = findViewById(R.id.btn_pass);

        btn_pass.setOnClickListener(this);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.contentlayout, new ResultFragment());
        transaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //如果现在处于停止状态，重新被用户使用
        AppLogger.d("------Demo2Activity------onRestart: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        //用户被看到
        AppLogger.d("------Demo2Activity------onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取用户焦点
        AppLogger.d("------Demo2Activity------onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //失去用户焦点
        AppLogger.d("------Demo2Activity------onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //完全被其他界面遮挡时
        AppLogger.d("------Demo2Activity------onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果直接点击返回
        AppLogger.d("------Demo2Activity------onDestroy: ");
    }

}
