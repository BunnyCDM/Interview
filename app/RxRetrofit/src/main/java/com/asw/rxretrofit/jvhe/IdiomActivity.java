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
import com.asw.rxretrofit.bean.IdiomDic;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class IdiomActivity extends RxAppCompatActivity {
    private ImageView iv_left;
    private LinearLayout ll_left;
    private TextView tv_title,
            tv_idiom_shoubu,
            tv_idiom_citou,
            tv_idiom_pinyin,
            tv_idiom_chengyujs,
            tv_idiom_from,
            tv_idiom_example,
            tv_idiom_yufa,
            tv_idiom_ciyujs,
            tv_idiom_yinzhengjs,
            tv_idiom_yongyici,
            tv_idiom_fanyici;
    private Button btn_idiom_ok;
    private EditText et_idiom;

    String Api_Key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_chengyu);
        initViews();
        initData();
    }

    private void initViews() {

        et_idiom = (EditText) findViewById(R.id.et_idiom);
        btn_idiom_ok = (Button) findViewById(R.id.btn_idiom_ok);
        ll_left = (LinearLayout) findViewById(R.id.ll_left);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        tv_idiom_shoubu = (TextView) findViewById(R.id.tv_idiom_shoubu);
        tv_idiom_citou = (TextView) findViewById(R.id.tv_idiom_citou);
        tv_idiom_pinyin = (TextView) findViewById(R.id.tv_idiom_pinyin);
        tv_idiom_chengyujs = (TextView) findViewById(R.id.tv_idiom_chengyujs);
        tv_idiom_from = (TextView) findViewById(R.id.tv_idiom_from);
        tv_idiom_example = (TextView) findViewById(R.id.tv_idiom_example);
        tv_idiom_yufa = (TextView) findViewById(R.id.tv_idiom_yufa);
        tv_idiom_ciyujs = (TextView) findViewById(R.id.tv_idiom_ciyujs);
        tv_idiom_yinzhengjs = (TextView) findViewById(R.id.tv_idiom_yinzhengjs);
        tv_idiom_yongyici = (TextView) findViewById(R.id.tv_idiom_yongyici);
        tv_idiom_fanyici = (TextView) findViewById(R.id.tv_idiom_fanyici);


    }

    private void initData() {
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("成语词典");
        ll_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RxView.clicks(btn_idiom_ok).throttleFirst(3, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                NetWork.getIdiomApi().getIdiomInfo(et_idiom.getText().toString(), Api_Key)
                        .compose(IdiomActivity.this.<IdiomDic>bindToLifecycle())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<IdiomDic>() {
                            @Override
                            public void call(IdiomDic idiomDic) {
                                setDisplay(idiomDic);
                            }
                        });
            }
        });
    }


    //在界面展示结果
    private void setDisplay(IdiomDic idiomDic) {
        if (idiomDic.getError_code() == 0) {
            Toast.makeText(IdiomActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
            tv_idiom_shoubu.setText("首字部首:" + idiomDic.getResult().getBushou());
            tv_idiom_citou.setText("成语词头:" + idiomDic.getResult().getHead());
            tv_idiom_pinyin.setText("拼音:" + idiomDic.getResult().getPinyin());
            tv_idiom_chengyujs.setText("成语解释:" + idiomDic.getResult().getChengyujs());
            tv_idiom_from.setText("成语出处:" + idiomDic.getResult().getFrom_());
            tv_idiom_example.setText("举例:" + idiomDic.getResult().getExample());
            tv_idiom_yufa.setText("语法:" + idiomDic.getResult().getYufa());
            tv_idiom_ciyujs.setText("词语解释:" + idiomDic.getResult().getCiyujs());
            tv_idiom_yinzhengjs.setText("引证解释:" + idiomDic.getResult().getYinzhengjs());
            if (idiomDic.getResult().getTongyi() != null)
                tv_idiom_yongyici.setText("同义词:" + idiomDic.getResult().getTongyi().get(0));
            if (idiomDic.getResult().getFanyi() != null)
                tv_idiom_fanyici.setText("反义词:" + idiomDic.getResult().getFanyi().get(0));
        } else {
            Toast.makeText(IdiomActivity.this, idiomDic.getReason(), Toast.LENGTH_SHORT).show();
        }
    }
}
