package com.example.viewstub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * ViewStub的优势在于在某些场景中，并不一定需要把所有的内容都展示出来，可以隐藏一些View视图，
 * 待用户需要展示的时候再加载到当前的Layout中，这个时候就可以用到ViewStub这个控件了，这样可
 * 以减少资源的消耗，使最初的加载速度变快
 * https://blog.csdn.net/crazymo_/article/details/78742884
 */
public class MainActivity extends AppCompatActivity {


    private ViewStub viewStub;
    private View parentContainer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        viewStub = findViewById(R.id.contentPanel);
        viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                Log.d("bunny", "onInflate: ");
            }
        });
    }


    public void showViewStub(View view) {
        showViewStub();
    }

    private void showViewStub() {
        try {
            Log.e("bunny", "ViewStub load before! viewStub==null " + (viewStub == null));
            parentContainer = (LinearLayout) viewStub.inflate();
            textView = (TextView) parentContainer.findViewById(R.id.tv_erro_tips);
            textView.setText("不好意思，还未录入任何数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void hideViewStub(View view) {
        viewStub.setVisibility(View.GONE);
    }


}
