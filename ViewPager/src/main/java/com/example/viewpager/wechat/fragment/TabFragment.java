package com.example.viewpager.wechat.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.baselibrary.utils.log.L;
import com.example.viewpager.R;
import com.example.viewpager.wechat.MainActivity;

/**
 * Created by mac on 2019-09-07.
 */
public class TabFragment extends Fragment {

    private TextView mTvTitle;
    private String mTitle;

    private final static String BUNDLR_KEY_TITLE = "key_title";

    public static TabFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLR_KEY_TITLE, title);

        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguements = getArguments();
        if (arguements != null) {
            mTitle = arguements.getString(BUNDLR_KEY_TITLE, "");
        }
        AppLogger.d("onCreate: mTitle=" + mTitle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppLogger.d("onCreateView: mTitle=" + mTitle);
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppLogger.d("onViewCreated: mTitle=" + mTitle);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvTitle.setText(mTitle);
        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取activity对象
                //写法一
//                MainActivity activity = (MainActivity) getActivity();
//                activity.changeWeChatTab("微信 changed！");
                //写法二
                Activity activity2 = getActivity();
                if (activity2 instanceof MainActivity) {
                    ((MainActivity) activity2).changeWeChatTab("微信 changed！");
                }
                //写法三：Fragment触发事件，Activity去响应事件
//                if (mListner != null) {
//                    mListner.onClick("微信 changed！");
//                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppLogger.d("onDestroyView: mTitle=" + mTitle);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        AppLogger.d("onDestroy: mTitle=" + mTitle);
    }

    public void changeTitle(String title) {
        if (!isAdded()) { // 或者isResumed()
            return;
        }
        mTvTitle.setText(title);
    }

    public interface OnTitleClickListener {
        void onClick(String title);
    }

    private OnTitleClickListener mListner;

    public void setOnTitleClickListener(OnTitleClickListener listener) {
        mListner = listener;
    }

}
