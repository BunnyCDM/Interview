package com.example.android_webview_js;

import android.util.Log;
import android.webkit.JavascriptInterface;


/**
 * Created by mac on 2020-04-20.
 */
public class JsInterface {

    private JsBridge jsBridge;

    public JsInterface(JsBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    /**
     * 这个不在主线长执行
     *
     * @param value
     */
    @JavascriptInterface //让webview识别
    public void setValue(String value) {
        Log.d("bunny", "setValue: value=" + value);
        jsBridge.setTextViewValue(value);
    }

}
