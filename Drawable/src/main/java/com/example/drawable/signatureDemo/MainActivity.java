package com.example.drawable.signatureDemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.drawable.R;

//手写签名Demo
//https://blog.csdn.net/venusic/article/details/52096710
public class MainActivity extends Activity {

    private ImageView imageSign;
    private PaintView mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        imageSign = (ImageView) findViewById(R.id.iv_sign);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.tablet_view);

        mView = new PaintView(this);
        frameLayout.addView(mView);
        mView.requestFocus();

        findViewById(R.id.tablet_clear).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mView.clear();
            }
        });

        findViewById(R.id.tablet_ok).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bitmap imageBitmap = mView.getCachebBitmap();
                imageSign.setImageBitmap(imageBitmap);
            }
        });
    }

//    class PaintView extends View {
//        private Paint paint;
//        private Canvas cacheCanvas;
//        private Bitmap cachebBitmap;
//        private Path path;
//
//        public PaintView(Context context) {
//            super(context);
//            init();
//        }
//
//        private void init() {
//            paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setStrokeWidth(3);
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setColor(Color.BLACK);
//            path = new Path();
//            cachebBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
//            cacheCanvas = new Canvas(cachebBitmap);
//            cacheCanvas.drawColor(Color.WHITE);
//        }
//
//        public Bitmap getCachebBitmap() {
//            return cachebBitmap;
//        }
//
//        public void clear() {
//            if (cacheCanvas != null) {
//                paint.setColor(Color.WHITE);
//                cacheCanvas.drawPaint(paint);
//                paint.setColor(Color.BLACK);
//                cacheCanvas.drawColor(Color.WHITE);
//                invalidate();
//            }
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            // canvas.drawColor(BRUSH_COLOR);
//            canvas.drawBitmap(cachebBitmap, 0, 0, null);
//            canvas.drawPath(path, paint);
//        }
//
//        @Override
//        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//            int curW = cachebBitmap != null ? cachebBitmap.getWidth() : 0;
//            int curH = cachebBitmap != null ? cachebBitmap.getHeight() : 0;
//            if (curW >= w && curH >= h) {
//                return;
//            }
//
//            if (curW < w)
//                curW = w;
//            if (curH < h)
//                curH = h;
//
//            Bitmap newBitmap = Bitmap.createBitmap(curW, curH, Bitmap.Config.ARGB_8888);
//            Canvas newCanvas = new Canvas();
//            newCanvas.setBitmap(newBitmap);
//            if (cachebBitmap != null) {
//                newCanvas.drawBitmap(cachebBitmap, 0, 0, null);
//            }
//            cachebBitmap = newBitmap;
//            cacheCanvas = newCanvas;
//        }
//
//        private float cur_x, cur_y;
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//
//            float x = event.getX();
//            float y = event.getY();
//
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN: {
//                    cur_x = x;
//                    cur_y = y;
//                    path.moveTo(cur_x, cur_y);
//                    break;
//                }
//                case MotionEvent.ACTION_MOVE: {
//                    path.quadTo(cur_x, cur_y, x, y);
//                    cur_x = x;
//                    cur_y = y;
//                    break;
//                }
//                case MotionEvent.ACTION_UP: {
//                    cacheCanvas.drawPath(path, paint);
//                    path.reset();
//                    break;
//                }
//            }
//            invalidate();
//            return true;
//        }
//    }

}