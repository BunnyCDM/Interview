package com.example.fragment.demo2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.fragment.R;

/**
 * Created by mac on 2020-04-11.
 */
public class ResourceFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //表示当activity和fragment产生关联时回调的方法
        AppLogger.d("------ResourceFragment------onAttach: Activity");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AppLogger.d("------ResourceFragment------onAttach: Context");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //表示当fragment第一次被创建时回调的方法
        AppLogger.d("------ResourceFragment------onCreate:");

        listener = (MyListener) getActivity();//等同于下面那个setListener
    }


    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    private MyListener listener;

    //定义接口，接口定义回调传数据的函数
    public interface MyListener {
        public abstract void sendMessage(String str);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resource, null);

        EditText editText = view.findViewById(R.id.et_content);
        Button button = view.findViewById(R.id.btn_pass);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = editText.getText().toString();
                listener.sendMessage(info);
            }
        });
        //表示当fragment第一次绘制用户界面时回调的方法
        AppLogger.d("------ResourceFragment------onCreateView:");
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //表示当fragment所属的activity创建成功时回调的方法
        AppLogger.d("------ResourceFragment------onActivityCreated:");
    }

    @Override
    public void onStart() {
        super.onStart();
        //表示当fragment能够被用户看到时回调的方法
        AppLogger.d("------ResourceFragment------onStart:");
    }

    @Override
    public void onResume() {
        super.onResume();
        //表示当fragment能够获取用户焦点时回调的方法
        AppLogger.d("------ResourceFragment------onResume:");
    }

    @Override
    public void onPause() {
        super.onPause();
        //表示当fragment失去用户焦点时回调的方法
        AppLogger.d("------ResourceFragment------onPause:");
    }

    @Override
    public void onStop() {
        super.onStop();
        //表示当fragment完全被用户遮挡时回调的方法
        AppLogger.d("------ResourceFragment------onStop:");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //表示fragment视图在activity中移除时回调的方法
        AppLogger.d("------ResourceFragment------onDestroyView:");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //表示当fragment被销毁时回调的方法
        AppLogger.d("------ResourceFragment------onDestroy:");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //表示当fragment与activity失去关联时回调的方法
        AppLogger.d("------ResourceFragment------onDetach:");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        AppLogger.d("------ResourceFragment------onHiddenChanged: hidden=" + hidden);
    }

}
