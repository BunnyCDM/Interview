package com.example.listpreference;

import android.content.Context;
import android.os.Build;
import android.preference.ListPreference;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

public class IjkListPreference extends ListPreference {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IjkListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IjkListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IjkListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IjkListPreference(Context context) {
        super(context);
    }
}
