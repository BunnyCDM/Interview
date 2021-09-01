package com.example.fragment.demo2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.fragment.R;

/**
 * Created by mac on 2019/2/26.
 * <p>
 * 演示DialogFragment的使用
 */

public class Demo2_1_1_1_1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2_1_1_1_1);
        AppLogger.d("------Demo2_1_1_1_1Activity------onCreate: ");

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment dialogFragment=new MyDialogFragment();
                dialogFragment.show(getFragmentManager(),"dialog");

//                final AlertDialog.Builder dialog = new AlertDialog.Builder(Demo2_1_1_1_1Activity.this);
//                dialog.setTitle("提示");
//                dialog.setMessage("是否退出当前账户");
//                dialog.setPositiveButton("退出", new android.content.DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//                    }
//                });
//                dialog.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        arg0.dismiss();
//                    }
//                });
//                dialog.show();
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //如果现在处于停止状态，重新被用户使用
        AppLogger.d("------Demo2_1_1_1_1Activity------onRestart: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        //用户被看到
        AppLogger.d("------Demo2_1_1_1_1Activity------onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取用户焦点
        AppLogger.d("------Demo2_1_1_1_1Activity------onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //失去用户焦点
        AppLogger.d("------Demo2_1_1_1_1Activity------onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //完全被其他界面遮挡时
        AppLogger.d("------Demo2_1_1_1_1Activity------onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果直接点击返回
        AppLogger.d("------Demo2_1_1_1_1Activity------onDestroy: ");
    }

}
