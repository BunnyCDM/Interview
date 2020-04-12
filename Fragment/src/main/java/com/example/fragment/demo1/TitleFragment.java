package com.example.fragment.demo1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.fragment.R;

/**
 * Created by mac on 2020-04-11.
 */
public class TitleFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //表示当activity和fragment产生关联时回调的方法
        AppLogger.d("------TitleFragment------onAttach: Activity");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AppLogger.d("------TitleFragment------onAttach: Context");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //表示当fragment第一次被创建时回调的方法
        AppLogger.d("------TitleFragment------onCreate:");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, null);
        RelativeLayout relativeLayout=view.findViewById(R.id.rl_layout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"TitleFragment",Toast.LENGTH_SHORT).show();
            }
        });
        //表示当fragment第一次绘制用户界面时回调的方法
        AppLogger.d("------TitleFragment------onCreateView:");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //表示当fragment所属的activity创建成功时回调的方法
        AppLogger.d("------TitleFragment------onActivityCreated:");
    }

    @Override
    public void onStart() {
        super.onStart();
        //表示当fragment能够被用户看到时回调的方法
        AppLogger.d("------TitleFragment------onStart:");
    }

    @Override
    public void onResume() {
        super.onResume();
        //表示当fragment能够获取用户焦点时回调的方法
        AppLogger.d("------TitleFragment------onResume:");
    }

    @Override
    public void onPause() {
        super.onPause();
        //表示当fragment失去用户焦点时回调的方法
        AppLogger.d("------TitleFragment------onPause:");
    }

    @Override
    public void onStop() {
        super.onStop();
        //表示当fragment完全被用户遮挡时回调的方法
        AppLogger.d("------TitleFragment------onStop:");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //表示fragment视图在activity中移除时回调的方法
        AppLogger.d("------TitleFragment------onDestroyView:");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //表示当fragment被销毁时回调的方法
        AppLogger.d("------TitleFragment------onDestroy:");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //表示当fragment与activity失去关联时回调的方法
        AppLogger.d("------TitleFragment------onDetach:");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        AppLogger.d("------TitleFragment------onHiddenChanged: hidden=" + hidden);
    }

}
