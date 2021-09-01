package com.example.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class DrawableActivity extends AppCompatActivity {
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        initView();
        //initData1();
        initData2();


    }


    private void initView() {
        mImageView = (ImageView) findViewById(R.id.iv_ImageView);
    }

    private void initData1() {
        Resources resources = getResources();//创建资源对象
        Drawable[] drawables = new Drawable[3];
        drawables[0] = resources.getDrawable(R.drawable.nba_1);
        drawables[1] = resources.getDrawable(R.drawable.nba_2);
        drawables[2] = resources.getDrawable(R.drawable.nba_3);
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        //mImageView.setImageDrawable(layerDrawable);//代码方式
        mImageView.setImageResource(R.drawable.layerdrawable);//xml方式

    }

    private void initData2() {
        //使用BitmapFactory  可从资源files, streams, and byte-arrays中解码生成Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nba_3);//获取位图
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);//转化成BitmapDrawable对象
        bitmapDrawable.setTileModeXY(TileMode.MIRROR, TileMode.MIRROR);
        bitmapDrawable.setAntiAlias(true);
        bitmapDrawable.setDither(true);
        //mImageView.setImageDrawable(bitmapDrawable);//代码方式
        mImageView.setImageResource(R.drawable.bitmapdrawable);//xml方式
    }

}
