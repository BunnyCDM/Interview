package com.asw.rxretrofit.jvhe;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.asw.rxretrofit.R;
import com.asw.rxretrofit.RetrofitInterface.NetWork;
import com.asw.rxretrofit.adapter.HistoryAdapter;
import com.asw.rxretrofit.bean.HistoryToday;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.text.SimpleDateFormat;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HistoryTodayActivity extends RxAppCompatActivity {
    private ImageView iv_left;
    private LinearLayout ll_left;
    private TextView tv_title;
    private ListView lv_history_today;
    private HistoryAdapter mhistoryadapter;

    String Api_Key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_history_today);

        initView();
        initData();
    }

    private void initView() {
        lv_history_today = (ListView) findViewById(R.id.lv_history);
        ll_left = (LinearLayout) findViewById(R.id.ll_left);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_left = (ImageView) findViewById(R.id.iv_left);
    }

    private void initData() {
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("历史上的今天");
        ll_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SimpleDateFormat ptime = new SimpleDateFormat("M/d");
        String month_day = ptime.format(new java.util.Date());

        NetWork.getHistorytodatApi().getHistoryTodayInfo(month_day, Api_Key)
                .compose(this.<HistoryToday>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HistoryToday>() {
                    @Override
                    public void call(HistoryToday historyToday) {
                        setDisplay(historyToday);
                    }
                });
    }

    //绑定Adapter
    private void setDisplay(HistoryToday response) {
        mhistoryadapter = new HistoryAdapter(this, response.getResult());
        lv_history_today.setAdapter(mhistoryadapter);
    }
}
