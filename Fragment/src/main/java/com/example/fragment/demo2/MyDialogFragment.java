package com.example.fragment.demo2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.fragment.R;

/**
 * Created by mac on 2020-04-12.
 *
 * 至少实现onCreateView（）或者onCreateDialog（）方法
 * onCreateView（）表示xml布局文件的形式展示dialog
 * onCreateDialog（）表示利用alertdialog或者dialog创建
 */
public class MyDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        builder.setMessage("您确定要退出嘛");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

}
