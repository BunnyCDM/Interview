package com.example.framemodel;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.framemodel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    Account account;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        account = new Account("bunny", 100);
        binding.setAccount(account);

        binding.setActivity(this);

    }


    //方法引用
    public void onClick(View view) {
        account.setLevel(account.getLevel() + 1);
//        binding.setAccount(account);
    }
}
