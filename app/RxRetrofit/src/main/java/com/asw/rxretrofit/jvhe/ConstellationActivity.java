package com.asw.rxretrofit.jvhe;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asw.rxretrofit.R;
import com.asw.rxretrofit.RetrofitInterface.NetWork;
import com.asw.rxretrofit.bean.Constellation;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ConstellationActivity extends RxAppCompatActivity {
    private ImageView iv_left;
    private LinearLayout ll_left;
    private TextView tv_title,
            tv_con_name,
            tv_con_datetime,
            tv_con_all,
            tv_con_color,
            tv_con_health,
            tv_con_love,
            tv_con_work,
            tv_con_money,
            tv_con_number,
            tv_con_qfriend,
            tv_con_summary;
    private Button btn_ok;
    private EditText et_name;
    private String Api_Key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_constellation);
        initView();
        initData();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_constellation);
        btn_ok = (Button) findViewById(R.id.btn__constellation_ok);
        ll_left = (LinearLayout) findViewById(R.id.ll_left);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        tv_con_name = (TextView) findViewById(R.id.tv_con_name);
        tv_con_datetime = (TextView) findViewById(R.id.tv_con_datetime);
        tv_con_all = (TextView) findViewById(R.id.tv_con_all);
        tv_con_color = (TextView) findViewById(R.id.tv_con_color);
        tv_con_health = (TextView) findViewById(R.id.tv_con_health);
        tv_con_love = (TextView) findViewById(R.id.tv_con_love);
        tv_con_work = (TextView) findViewById(R.id.tv_con_work);
        tv_con_money = (TextView) findViewById(R.id.tv_con_money);
        tv_con_number = (TextView) findViewById(R.id.tv_con_number);
        tv_con_qfriend = (TextView) findViewById(R.id.tv_con_qfriend);
        tv_con_summary = (TextView) findViewById(R.id.tv_con_summary);
    }


    private void initData() {
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("星座运势");
        ll_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RxView.clicks(btn_ok).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        NetWork.getConstellationApi().getConstellation(et_name.getText().toString().trim(), "today", Api_Key)
                                .compose(ConstellationActivity.this.<Constellation>bindToLifecycle())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<Constellation>() {
                                    @Override
                                    public void call(Constellation constellation) {
                                        setResult(constellation);
                                    }
                                });
                    }
                });

    }


    //将结果显示到主界面
    private void setResult(Constellation consbean) {
        if (consbean.getError_code() == 0) {
            Toast.makeText(ConstellationActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ConstellationActivity.this, "查询失败", Toast.LENGTH_SHORT).show();

        }

        tv_con_name.setText(consbean.getName());
        tv_con_datetime.setText(consbean.getDatetime());
        tv_con_all.setText("综合指数:  " + consbean.getAll());
        tv_con_color.setText("幸运色:  " + consbean.getColor());
        tv_con_health.setText("健康指数:  " + consbean.getHealth());
        tv_con_love.setText("爱情指数:  " + consbean.getLove());
        tv_con_work.setText("工作指数:  " + consbean.getWork());
        tv_con_money.setText("财运指数:  " + consbean.getMoney());
        tv_con_number.setText("幸运数字:  " + consbean.getNumber());
        tv_con_qfriend.setText("速配星座:  " + consbean.getQFriend());
        tv_con_summary.setText("今日概述:  " + consbean.getSummary());
    }

}
