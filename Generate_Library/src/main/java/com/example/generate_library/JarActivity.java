package com.example.generate_library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by mac on 2019-11-26.
 */
public class JarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(MResource.getIdByName(this, "layout", "jar_layout"));

        ImageView mPlayerLogo = (ImageView) this.findViewById(MResource.getIdByName(this, "id", "logo"));

        mPlayerLogo.setImageResource(MResource.getIdByName(this, "drawable", "cheese2"));
    }
}
