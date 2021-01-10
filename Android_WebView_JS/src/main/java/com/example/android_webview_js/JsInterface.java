//package com.example.android_webview_js;
//
//import android.webkit.JavascriptInterface;
//
//import com.example.baselibrary.utils.log.AppLogger;
//
///**
// * Created by mac on 2020-04-20.
// */
//public class JsInterface {
//
//    private JsBridge jsBridge;
//
//    public JsInterface(JsBridge jsBridge) {
//        this.jsBridge = jsBridge;
//    }
//
//    /**
//     * 这个不在主线长执行
//     *
//     * @param value
//     */
//    @JavascriptInterface //让webview识别
//    public void setValue(String value) {
//        AppLogger.d("setValue: value=" + value);
//        jsBridge.setTextViewValue(value);
//    }
//
//}
