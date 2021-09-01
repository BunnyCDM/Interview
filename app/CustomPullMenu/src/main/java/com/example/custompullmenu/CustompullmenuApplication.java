package com.example.custompullmenu;

import android.app.Application;

/**
 * Created by mac on 2019-11-16.
 */
public class CustompullmenuApplication extends Application {


    public static CustompullmenuApplication AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext=this;
    }


}
