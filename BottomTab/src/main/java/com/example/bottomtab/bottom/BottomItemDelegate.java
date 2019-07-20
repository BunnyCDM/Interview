package com.example.bottomtab.bottom;

import android.widget.Toast;

import com.example.bottomtab.R;
import com.example.bottomtab.app.Latte;
import com.example.bottomtab.delegate.LatteDelegate;

/**
 * Created by mac on 2019/2/25.
 */

public abstract class BottomItemDelegate extends LatteDelegate {

    private static final long WAIT_TIME = 2000l;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity
                    , "" + "双击退出" + Latte.getApplicationContext().getString(R.string.app_name)
                    , Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
