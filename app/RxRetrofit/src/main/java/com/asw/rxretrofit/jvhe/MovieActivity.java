package com.asw.rxretrofit.jvhe;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.asw.rxretrofit.R;
import com.asw.rxretrofit.RetrofitInterface.NetWork;
import com.asw.rxretrofit.adapter.MovieAdapter;
import com.asw.rxretrofit.bean.MovieBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieActivity extends RxAppCompatActivity implements View.OnClickListener {
    private ImageView iv_left;
    private LinearLayout ll_left;
    private TextView tv_title;
    private ListView lv_movie;
    private MovieAdapter mmovieadapter;
    private Button btn_cn, btn_hk, btn_us;
    String Api_Key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_movie);
        btn_cn = (Button) findViewById(R.id.btn_movie_cn);
        btn_hk = (Button) findViewById(R.id.btn_movie_hk);
        btn_us = (Button) findViewById(R.id.btn_movie_us);
        btn_cn.setOnClickListener(this);
        btn_hk.setOnClickListener(this);
        btn_us.setOnClickListener(this);
        ll_left = (LinearLayout) findViewById(R.id.ll_left);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        lv_movie = (ListView) findViewById(R.id.lv_movie);
    }

    private void initData() {
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("电影票房");
        ll_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_movie_cn:
                getMoviesByArea("CN");
                break;
            case R.id.btn_movie_hk:
                getMoviesByArea("HK");
                break;
            case R.id.btn_movie_us:
                getMoviesByArea("US");
                break;
        }
    }

    private void getMoviesByArea(String area) {
        NetWork.getMoviesApi().getMovies(area, Api_Key)
                .compose(this.<MovieBean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieBean>() {
                    @Override
                    public void call(MovieBean movieBean) {
                        setDisplay(movieBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(MovieActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //将结果展示出来
    private void setDisplay(MovieBean movietinfo) {
        mmovieadapter = new MovieAdapter(MovieActivity.this, movietinfo.getResult());
        mmovieadapter.notifyDataSetChanged();
        lv_movie.setAdapter(mmovieadapter);
        setListViewHeightBasedOnChildren(lv_movie);
    }

    //计算ListView的高度
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
