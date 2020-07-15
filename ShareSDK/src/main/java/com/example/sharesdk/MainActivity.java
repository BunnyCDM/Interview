package com.example.sharesdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;
import com.mob.MobSDK;
import com.mob.OperationCallback;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.TextView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnekeyShare oks = new OnekeyShare();

                // title标题，微信、QQ和QQ空间等平台使用
                oks.setTitle("title标题");

                // titleUrl QQ和QQ空间跳转链接
                oks.setTitleUrl("http://sharesdk.cn");

                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");

                // setImageUrl是网络图片的url
                oks.setImageUrl("https://hmls.hfbank.com.cn/hfapp-api/9.png");

                // url在微信、Facebook等平台中使用
                oks.setUrl("http://sharesdk.cn");

                // 启动分享GUI
                oks.show(MobSDK.getContext());

            }
        });

        findViewById(R.id.TextView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QQ.ShareParams sp = new QQ.ShareParams();
                sp.setTitle("title标题");
                sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
                sp.setUrl("http://sharesdk.cn");
                sp.setText("我是分享文本");
                sp.setShareType(Platform.SHARE_WEBPAGE);
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        AppLogger.d("onComplete: "+Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        AppLogger.d("onError: "+Thread.currentThread().getName());
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        AppLogger.d("onCancel: "+Thread.currentThread().getName());
                    }
                });
                // 执行图文分享
                qq.share(sp);
            }
        });

        findViewById(R.id.TextView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.TextView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
