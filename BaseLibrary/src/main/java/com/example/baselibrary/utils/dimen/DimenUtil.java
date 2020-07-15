package com.example.baselibrary.utils.dimen;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by mac on 2020-05-12.
 * ScreenUtils
 */
public class DimenUtil {

    private static DisplayMetrics getDisplayMetrics(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm;
    }

    public static int getScreenWidth(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDensityDPI(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.density;
    }


    /**
     * dip-->px
     *
     * @param dip
     * @return
     */
    public static int dip2px( Context context,int dip) {
        //知道px和dp转换关系
        /*
            1.px/(ppi/160)=dp
            2.px/dp = density
         */

        float density = context.getResources().getDisplayMetrics().density;
        int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        //px=dp*density
        int px = (int) (dip * density + .5f);
        return px;
    }

    /**
     * px-->dip
     *
     * @param px
     * @return
     */
    public static int px2dp(Context context,int px) {
        float density = context.getResources().getDisplayMetrics().density;
        int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        //px=dp*density
        int dip = (int) (px / density + .5f);
        return dip;
    }

}
