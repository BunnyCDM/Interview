package com.example.iconify;

import android.app.Application;

import com.example.iconify.app.Iconify;
import com.example.iconify.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by mac on 2019/2/22.
 */

public class IconifyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())//自定义字体
                .configure();
    }
}
