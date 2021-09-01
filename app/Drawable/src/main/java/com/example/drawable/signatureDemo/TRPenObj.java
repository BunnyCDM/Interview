package com.example.drawable.signatureDemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2019/4/1.
 */
public class TRPenObj implements Serializable {

    private final static long serialVersionUID = 1L;
    private long mTime;
    private int mColor;
    private float mThick;
    private List<Point> mPenPointList;

    public TRPenObj(long mTime, int mColor, float mThick, List<Point> mPenPointList) {
        this.mTime = mTime;
        this.mColor = mColor;
        this.mThick = mThick;
        this.mPenPointList = mPenPointList;
    }

    public void addPenPoint(int posX, int posY) {
        mPenPointList.add(new Point(posX, posY));
    }

    public boolean drawObj(Canvas canvas, IWBPosTrans trans) {

        if (canvas == null || trans == null || mPenPointList == null && mPenPointList.size() < 1) {
            return false;
        }

        Paint paint = new Paint();
        paint.setColor(mColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(mThick);
        paint.setAntiAlias(true);

        Path path = new Path();
        boolean bStart = false;
        float wbPathX = 0;
        float wbPathY = 0;
        float wbLastX = 0;
        float wbLastY = 0;
        for (Point ptPen : mPenPointList) {

            float wbPosX = trans.transPosX(ptPen.x);
            float wbPosY = trans.transPosY(ptPen.y);
            if (wbPosX < 0 || wbPosY < 0) {
                bStart = false;
            } else {
                if (bStart) {
                    wbPathX = (wbPosX + wbLastX) / 2;
                    wbPathY = (wbPosY + wbLastY) / 2;
                    wbLastX = wbPosX;
                    wbLastY = wbPosY;
                    path.lineTo(wbPathX, wbPathY);
                } else {
                    wbPathX = wbPosX;
                    wbPathY = wbPosY;
                    wbLastX = wbPosX;
                    wbLastY = wbPosY;
                    path.moveTo(wbPathX, wbPathY);
                    bStart = true;
                }

            }
        }

        canvas.drawPath(path, paint);
        return true;
    }
}
