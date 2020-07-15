package com.example.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.baselibrary.utils.log.AppLogger;

/**
 * Created by mac on 2020-07-08.
 * <p>
 * 旋转方块
 */
public class RotatingRect extends View {

    public RotatingRect(Context context) {
        this(context, null);
    }

    public RotatingRect(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotatingRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    Paint mPaint;
    private float degress = 0;

    private void init() {
        AppLogger.d("init: ");
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        AppLogger.d("onDraw:");
        //绘制前保存下
        canvas.save();

        //向下移动
        canvas.translate(200, 200);
        //旋转角度
        //canvas.rotate(degress);
        canvas.rotate(degress, 50, 50);

        canvas.drawRect(0, 0, 100, 100, mPaint);
        degress++;

        //绘制之后
        canvas.restore();

        // TODO: 2020-07-08 延迟绘制，较少资源使用
        invalidate();
    }

}
