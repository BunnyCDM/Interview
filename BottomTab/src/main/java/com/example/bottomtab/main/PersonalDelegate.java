package com.example.bottomtab.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.bottomtab.R;
import com.example.bottomtab.bottom.BottomItemDelegate;

/**
 * Created by mac on 2019/2/25.
 */

public class PersonalDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}