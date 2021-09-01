package com.example.fragment.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.fragment.R;

/**
 * Created by mac on 2019/2/26.
 */

public class TopLineFragment extends Fragment {
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //表示当activity和fragment产生关联时回调的方法
        AppLogger.d("------TopLineFragment------onAttach: Activity");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AppLogger.d("------TopLineFragment------onAttach: Context");
    }

    // TODO: 2020-04-11  比较重要的方法
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //表示当fragment第一次被创建时回调的方法
        AppLogger.d("------TopLineFragment------onCreate:");
    }

    // TODO: 2020-04-11  比较重要的方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topline, null);
        //表示当fragment第一次绘制用户界面时回调的方法
        AppLogger.d("------TopLineFragment------onCreateView:");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //表示当fragment所属的activity创建成功时回调的方法
        AppLogger.d("------TopLineFragment------onActivityCreated:");
    }

    @Override
    public void onStart() {
        super.onStart();
        //表示当fragment能够被用户看到时回调的方法
        AppLogger.d("------TopLineFragment------onStart:");
    }

    @Override
    public void onResume() {
        super.onResume();
        //表示当fragment能够获取用户焦点时回调的方法
        AppLogger.d("------TopLineFragment------onResume:");
    }

    // TODO: 2020-04-11  比较重要的方法
    @Override
    public void onPause() {
        super.onPause();
        //表示当fragment失去用户焦点时回调的方法
        AppLogger.d("------TopLineFragment------onPause:");
    }

    @Override
    public void onStop() {
        super.onStop();
        //表示当fragment完全被用户遮挡时回调的方法
        AppLogger.d("------TopLineFragment------onStop:");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //表示fragment视图在activity中移除时回调的方法
        AppLogger.d("------TopLineFragment------onDestroyView:");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //表示当fragment被销毁时回调的方法
        AppLogger.d("------TopLineFragment------onDestroy:");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //表示当fragment与activity失去关联时回调的方法
        AppLogger.d("------TopLineFragment------onDetach:");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        AppLogger.d("------TopLineFragment------onHiddenChanged: hidden=" + hidden);
    }
}
