package com.example.viewpager.five;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.viewpager.R;

/**
 * Created by mac on 2019-09-01.
 */
public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppLogger.d( "onCreateView: Fragment2");
        View view = inflater.inflate(R.layout.layout2_05, container, false);
        return view;
    }
}
