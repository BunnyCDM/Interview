package com.example.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.baselibrary.utils.dimen.DimenUtil;
import com.example.baselibrary.utils.log.AppLogger;

/**
 * Created by mac on 2020-07-08.
 */
public class MyCustomView extends AppCompatImageView {

    private String titleText = "Hello world";

    private int titleColor = Color.BLACK;
    private int titleBackgroundColor = Color.WHITE;
    private int titleSize = 16;
    private Context context;

    public MyCustomView(Context context) {
        this(context, null);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        AppLogger.d("MyCustomView: ");
        this.context = context;
        final Resources.Theme theme = context.getTheme();
        TypedArray a = theme.obtainStyledAttributes(attrs,
                R.styleable.MyCustomView, defStyleAttr, 0);
        if (null != a) {
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.MyCustomView_titleColor:
                        titleColor = a.getColor(attr, Color.BLACK);
                        break;
                    case R.styleable.MyCustomView_titleSize:
                        titleSize = a.getDimensionPixelSize(attr, titleSize);
                        break;
                    case R.styleable.MyCustomView_titleText:
                        titleText = a.getString(attr);
                        break;
                    case R.styleable.MyCustomView_titleBackgroundColor:
                        titleBackgroundColor = a.getColor(attr, Color.WHITE);
                        break;
                }
            }
            a.recycle();
            init();
        }
    }

    /**
     * 初始化
     */
    private Paint mPaint;
    private Rect mBound;

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(titleSize);
        /**
         * 得到自定义View的titleText内容的宽和高
         */
        mBound = new Rect();
        AppLogger.d("init: " + mBound.left + " " + mBound.top + " " + mBound.right + " " + mBound.bottom);
        mPaint.getTextBounds(titleText, 0, titleText.length(), mBound);
        AppLogger.d("init: " + mBound.left + " " + mBound.top + " " + mBound.right + " " + mBound.bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int mWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        AppLogger.d("onDraw: width is " + width + ",height is " + height + ",mWidth is " + mWidth + " ,mHeight is " + mHeight + ",  " + DimenUtil.px2dp(context, width));
        mPaint.setColor(titleBackgroundColor);
        canvas.drawCircle(width / 2f, width / 2f, width / 2f, mPaint);
        mPaint.setColor(titleColor);
        canvas.drawText(titleText, width / 2 - mBound.width() / 2, height / 2 + mBound.height() / 2, mPaint);
    }

}