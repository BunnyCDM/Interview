package com.asw.rxretrofit.jvhe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.asw.rxretrofit.R;
import com.asw.rxretrofit.RetrofitInterface.NetWork;
import com.asw.rxretrofit.adapter.TrainAdapter;
import com.asw.rxretrofit.bean.TrainTimeByStation;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TrainTimeActivity extends RxAppCompatActivity {
    private ImageView iv_left;
    private LinearLayout ll_left;
    private TextView tv_title;
    private EditText et_train_strat, et_train_end;
    private Button btn_train_station_ok;
    private ListView lv_station;

    String train_num, start, end;
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
        setContentView(R.layout.activity_train_time);
        lv_station = (ListView) findViewById(R.id.lv_train);
        ll_left = (LinearLayout) findViewById(R.id.ll_left);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        et_train_strat = (EditText) findViewById(R.id.et_train_strat);
        et_train_end = (EditText) findViewById(R.id.et_train_end);
        btn_train_station_ok = (Button) findViewById(R.id.btn_train_station_ok);


    }

    private void initData() {
        iv_left.setVisibility(View.VISIBLE);
        tv_title.setText("火车时刻查询");
        ll_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RxView.clicks(btn_train_station_ok).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        start = et_train_strat.getText().toString().trim();
                        end = et_train_end.getText().toString().trim();
                        if (start.isEmpty()) {
                            Toast.makeText(TrainTimeActivity.this, "出发站不能为空", Toast.LENGTH_SHORT).show();
                        } else if (end.isEmpty()) {
                            Toast.makeText(TrainTimeActivity.this, "到达站不能为空", Toast.LENGTH_SHORT).show();
                        }
                        //执行查询
                        else {
                            Toast.makeText(TrainTimeActivity.this, "开始查询...", Toast.LENGTH_SHORT).show();

                            NetWork.getTrainApi().getTrainStation(start, end, Api_Key)
                                    .compose(TrainTimeActivity.this.<TrainTimeByStation>bindToLifecycle())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(new Action1<TrainTimeByStation>() {
                                        @Override
                                        public void call(TrainTimeByStation trainTimeByStation) {
                                            setTrain_Station_Dispaly(trainTimeByStation);
                                        }
                                    });
                        }
                    }
                });

    }


    private void setTrain_Station_Dispaly(final TrainTimeByStation trainTimeByStation) {
        if (trainTimeByStation.getError_code() == 0) {
            Toast.makeText(TrainTimeActivity.this, trainTimeByStation.getReason(), Toast.LENGTH_SHORT).show();
            lv_station.setAdapter(new TrainAdapter(this, trainTimeByStation.getResult().getList()));
            lv_station.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Uri uri = Uri.parse(trainTimeByStation.getResult().getList().get(position).getM_chaxun_url());
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);
                }
            });
            setListViewHeightBasedOnChildren(lv_station);
        } else {
            Toast.makeText(TrainTimeActivity.this, trainTimeByStation.getReason(), Toast.LENGTH_SHORT).show();
        }
    }


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
