package com.kl.context;

import android.app.Application;
import android.content.res.Configuration;

import com.example.baselibrary.utils.log.AppLogger;

/**
 * Created by mac on 2020-06-23.
 * <p>
 * 真正的全局上下文对象
 */
public class App extends Application {

    private String textData = "default";

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.d("onCreate: ");
    }


    /**
     * 一般不会执行，只有在模拟情况下执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        AppLogger.d("onTerminate: ");
    }

    /**
     * 低内存，一般情况不会执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        AppLogger.d("onLowMemory: ");
    }

    /**
     * 清理内存执行
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        AppLogger.d("onTrimMemory: ");
    }

    /**
     * 配置信息改变执行
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AppLogger.d("onConfigurationChanged: ");
    }
}
