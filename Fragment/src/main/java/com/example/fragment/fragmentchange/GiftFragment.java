package com.example.fragment.fragmentchange;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragment.R;

/**
 * Created by mac on 2019/2/27.
 */

public class GiftFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gift,null);
        return view;
    }
}
