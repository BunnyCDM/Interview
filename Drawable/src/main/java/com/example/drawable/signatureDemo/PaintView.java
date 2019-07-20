package com.example.drawable.signatureDemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mac on 2019/3/31.
 * <p>
 * Paint画笔
 */
public class PaintView extends View {

    private final static String TAG = "bunny";
    private Paint mPaint; //画笔
    private Canvas mCanvas; //画布
    private Bitmap mBitmap;
    private Path mPath;

    public PaintView(Context context) {
        super(context);
        Log.e(TAG, "PaintView: 1");
        init(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "PaintView: 2");
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, "PaintView: 3");
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e(TAG, "PaintView: 4");
    }

    private void init(Context context) {
        mPaint = new Paint();//创建画笔
        mPaint.setAntiAlias(true);//设置画笔的锯齿效果
        mPaint.setStrokeWidth(3);//设置空心的边框宽度
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔风格，空心或者实心
        mPaint.setColor(Color.BLACK);//设置画笔颜色

        mPath = new Path();
        mBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);//以bitmap对象创建一个画布，则将内容都绘制在bitmap上，因此bitmap不得为null
        mCanvas.drawColor(Color.WHITE);//设置Canvas的背景颜色
    }

    public Bitmap getCachebBitmap() {
        return mBitmap;
    }

    public void clear() {
        if (mCanvas != null) {
            mPaint.setColor(Color.WHITE);
            mCanvas.drawPaint(mPaint);
            mPaint.setColor(Color.BLACK);
            mCanvas.drawColor(Color.WHITE);
            invalidate(); //通知View组件重绘
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: ");
        int curW = mBitmap != null ? mBitmap.getWidth() : 0;
        int curH = mBitmap != null ? mBitmap.getHeight() : 0;
        if (curW >= w && curH >= h) {
            return;
        }

        if (curW < w) {
            curW = w;
        }
        if (curH < h) {
            curH = h;
        }

        Bitmap newBitmap = Bitmap.createBitmap(curW, curH, Bitmap.Config.ARGB_8888);
        Canvas newCanvas = new Canvas();
        newCanvas.setBitmap(newBitmap);
        if (mBitmap != null) {
            newCanvas.drawBitmap(mBitmap, 0, 0, null);
        }
        mBitmap = newBitmap;
        mCanvas = newCanvas;
    }

    private float cur_x, cur_y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        final int action = event.getAction();
        Log.e(TAG, "onTouchEvent: action=" + action);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                cur_x = x;
                cur_y = y;
                mPath.moveTo(cur_x, cur_y);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                mPath.quadTo(cur_x, cur_y, x, y);
                cur_x = x;
                cur_y = y;
                break;
            }

            case MotionEvent.ACTION_UP: {
                mCanvas.drawPath(mPath, mPaint);
                mPath.reset();
                break;
            }
            default:
                break;
        }
        invalidate();
        return true;
    }

}
