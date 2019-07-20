package com.example.fragment.fragmentchange;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.fragment.R;

/**
 * Created by mac on 2019/2/27.
 * <p>
 * 演示点击标签切换对应的界面
 */

public class FragmentChangeActiviy extends Activity implements View.OnClickListener {


    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentchange);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.content_layout, new ShopRankFragment());
        transaction.commit();

        findViewById(R.id.rb_shoprand).setOnClickListener(this);
        findViewById(R.id.rb_share).setOnClickListener(this);
        findViewById(R.id.rb_gift).setOnClickListener(this);
        findViewById(R.id.rb_order).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.rb_shoprand:
                transaction.replace(R.id.content_layout, new ShopRankFragment());
                break;
            case R.id.rb_share:
                transaction.replace(R.id.content_layout, new ShareFragment());
                break;
            case R.id.rb_gift:
                transaction.replace(R.id.content_layout, new GiftFragment());
                break;
            case R.id.rb_order:
                transaction.replace(R.id.content_layout, new OrderFragment());
                break;
            default:
                break;
        }
        transaction.commit();

    }
}
