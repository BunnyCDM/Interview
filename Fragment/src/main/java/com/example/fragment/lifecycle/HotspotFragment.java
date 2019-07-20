package com.example.fragment.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragment.R;

/**
 * Created by mac on 2019/2/26.
 */

public class HotspotFragment extends Fragment {


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //表示当activity和fragment产生关联时回调的方法
        Log.d("tag", "------HotspotFragment------onAttach: Activity");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("tag", "------HotspotFragment------onAttach: Context");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //表示当fragment第一次被创建时回调的方法
        Log.d("tag", "------HotspotFragment------onCreate:");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotspot, null);
        //表示当fragment第一次绘制用户界面时回调的方法
        Log.d("tag", "------HotspotFragment------onCreateView:");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //表示当fragment所属的activity创建成功时回调的方法
        Log.d("tag", "------HotspotFragment------onActivityCreated:");
    }

    @Override
    public void onStart() {
        super.onStart();
        //表示当fragment能够被用户看到时回调的方法
        Log.d("tag", "------HotspotFragment------onStart:");
    }

    @Override
    public void onResume() {
        super.onResume();
        //表示当fragment能够获取用户焦点时回调的方法
        Log.d("tag", "------HotspotFragment------onResume:");
    }

    @Override
    public void onPause() {
        super.onPause();
        //表示当fragment失去用户焦点时回调的方法
        Log.d("tag", "------HotspotFragment------onPause:");
    }

    @Override
    public void onStop() {
        super.onStop();
        //表示当fragment完全被用户遮挡时回调的方法
        Log.d("tag", "------HotspotFragment------onStop:");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //表示fragment视图在activity中移除时回调的方法
        Log.d("tag", "------HotspotFragment------onDestroyView:");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //表示当fragment被销毁时回调的方法
        Log.d("tag", "------HotspotFragment------onDestroy:");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //表示当fragment与activity失去关联时回调的方法
        Log.d("tag", "------HotspotFragment------onDetach:");
    }
}
