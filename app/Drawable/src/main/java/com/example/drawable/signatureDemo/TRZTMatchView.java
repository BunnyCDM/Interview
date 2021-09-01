package com.example.drawable.signatureDemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.drawable.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2019/4/1.
 */
public class TRZTMatchView extends View {

    private List<TRPenObj> mPenListCalc;
    private Canvas mBufferCanvas;
    private Bitmap mBufferBitmap;
    private Bitmap mBGBitmap;

    private int mBufferWidth = 225;
    private int mBufferHeight = 225;

    public TRZTMatchView(Context context) {
        super(context);
    }

    public TRZTMatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(getResources().getColor(R.color.colorWBBG));
        mPenListCalc = new ArrayList<TRPenObj>();
        mBufferBitmap = Bitmap.createBitmap(mBufferWidth, mBufferHeight, Bitmap.Config.ARGB_8888);
        mBufferCanvas = new Canvas(mBufferBitmap);
    }

    public TRZTMatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TRZTMatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBufferBitmap == null || mBufferCanvas == null) {
            return;
        }

        if (mBufferBitmap != null) {
            Matrix matrix = new Matrix();
            final float w = getWidth();
            final float h = getHeight();
            if (w / mBufferWidth > h / mBufferHeight) {
                float scale = h / mBufferHeight;
                float offset = (w - mBufferWidth * scale) / 2;
                matrix.setScale(scale, scale);
                matrix.postTranslate(offset, 0);
            } else {
                float scale = w / mBufferWidth;
                float offset = (h - mBufferHeight * scale) / 2;
                matrix.setScale(scale, scale);
                matrix.postTranslate(0, offset);
            }
            canvas.drawColor(getResources().getColor(R.color.colorBG));
            canvas.drawBitmap(mBufferBitmap, matrix, null);

        }
    }

    public void ClearWB() {
        if (mBufferCanvas != null) {
            mPenListCalc.clear();
            reDraw(mBufferCanvas);
            invalidate();
        }
    }

    public void SetBG(Bitmap bg) {
        mBGBitmap = bg;
        ClearWB();
    }

    public void AddPenObj(TRPenObj newPenObj) {
        if (newPenObj != null) {
            mPenListCalc.add(newPenObj);
            reDraw(mBufferCanvas);
            invalidate();
        }
    }

    public TRZTMatchView.WBPosPenToBufferTrans mWBPosPenToBufferTrans = new TRZTMatchView.WBPosPenToBufferTrans();

    private class WBPosPenToBufferTrans implements IWBPosTrans {
        @Override
        public float transPosX(float posX) {
            return posX;
        }

        @Override
        public float transPosY(float posY) {
            return posY;
        }
    }

    protected void reDraw(Canvas canvas) {
        if (canvas == null)
            return;

        if (mBGBitmap != null && mBGBitmap.getWidth() > 0 && mBGBitmap.getHeight() > 0) {
            canvas.drawBitmap(mBGBitmap, 0, 0, null);
        } else {
            canvas.drawColor(getResources().getColor(R.color.colorWBBG));
        }

        for (TRPenObj pen : mPenListCalc) {
            pen.drawObj(canvas, mWBPosPenToBufferTrans);
        }
    }

}
