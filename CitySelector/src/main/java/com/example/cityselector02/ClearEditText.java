package com.example.cityselector02;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ClearEditText extends AppCompatEditText {

    private Drawable mClearDrawable;

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化右边的x按钮
    private void init() {
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            //通过选择器获取drawable的对象
            mClearDrawable = getResources()
                    .getDrawable(android.R.drawable.presence_offline);
        }

        //设置drawable的绘画区域
        mClearDrawable.setBounds(0, 0,
                mClearDrawable.getIntrinsicWidth(),
                mClearDrawable.getIntrinsicHeight());

        setIndicatorVisible(false);

        //对EditText进行监听text变化的事件
        addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO 在text改变的时候，作出反应
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }

    //控制x显示与隐藏,drawableLeft drawableTop drawableRight drawableBottom直接可以通过以下方法直接设置进去
    private void setIndicatorVisible(boolean visible) {
        Drawable drawable = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                drawable, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 判断x坐标，如果触摸的范围是在x图标上面，那么，就是点到了x图标，则调用上面的隐藏功能
        //	|---------------------------****---|
        //	|---------------------------****---|
        int x = (int) event.getX();
        //左边的x区域为：
        int left = getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth();
        //右边的x区域为：
        int right = getWidth() - getPaddingRight();

        //如果是判断在区域里面，直接setText("");

        return super.onTouchEvent(event);
    }
}
