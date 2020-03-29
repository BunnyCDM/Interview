package com.example.framemodel.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.framemodel.R;
import com.example.framemodel.databinding.ActivityMvvmBinding;

/**
 * Created by mac on 2020-03-28.
 */
public class MVVMActivity extends AppCompatActivity {

    ActivityMvvmBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        MVVMViewModel mvvmViewModel=new MVVMViewModel(getApplication(),binding);
        binding.setViewModel(mvvmViewModel);
    }
}
