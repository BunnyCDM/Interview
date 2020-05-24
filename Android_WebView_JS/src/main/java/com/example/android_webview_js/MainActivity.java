package com.example.android_webview_js;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;

public class MainActivity extends AppCompatActivity implements JsBridge {


    private WebView mWebView;
    private TextView mTvResult;
    private Handler mHandler;

    private EditText mEditText;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppLogger.d("onCreate: ");

        mWebView = findViewById(R.id.webView);
        mTvResult = findViewById(R.id.tv_result);
        mHandler = new Handler();

        mEditText = findViewById(R.id.edittext);
        mBtn = findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditText.getText().toString();
                mWebView.loadUrl("javascript:if(window.remote){window.remote('" + str + "')}");
            }
        });

        //允许WebView加载js代码
        mWebView.getSettings().setJavaScriptEnabled(true);

        //给WebView添加js接口
        mWebView.addJavascriptInterface(new JsInterface(this), "launcher");

        mWebView.loadUrl("file:///android_asset/index.html");

        //打开允许调试开关
        mWebView.setWebContentsDebuggingEnabled(true);
    }

    @Override
    public void setTextViewValue(final String value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTvResult.setText(value);
            }
        });
    }
}
