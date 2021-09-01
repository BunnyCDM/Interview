package com.example.iconify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.id_iv);

        //在代码中使用
        IconDrawable iconDrawable = new IconDrawable(this, FontAwesomeIcons.fa_key)
                .colorRes(R.color.colorAccent)
                .actionBarSize();

        imageView.setImageDrawable(iconDrawable);

    }
}
