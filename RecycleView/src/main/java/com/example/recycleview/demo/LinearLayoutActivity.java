package com.example.recycleview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.recycleview.R;
import com.mac.recyclerview.LRecyclerView;

/**
 * Created by mac on 2019-09-16.
 */
public class LinearLayoutActivity extends AppCompatActivity {

    private LRecyclerView mRecyclerView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_ll_activity);

        mRecyclerView = findViewById(R.id.list);

    }
}
