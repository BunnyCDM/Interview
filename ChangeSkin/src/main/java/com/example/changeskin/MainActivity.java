package com.example.changeskin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.changeskinmodule.ResourceManager;
import com.example.changeskinmodule.SkinManager;
import com.example.changeskinmodule.base.BaseSkinActivity;
import com.example.changeskinmodule.callback.ISkinChangingCallback;
import com.nineoldandroids.view.ViewHelper;

import java.io.File;
import java.lang.reflect.Method;

public class MainActivity extends BaseSkinActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private String mSkinPkgPath = Environment.getExternalStorageDirectory()
            + File.separator + "ChangeSkinPlugin-debug.apk";
    private String mSkinPlugPkg = "com.example.changeskinplugin";
    private String[] mDatas = new String[]{"Activity", "Service", "Activity", "Service", "Activity"
            , "Service", "Activity", "Service"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppLogger.d("onCreate: " + Environment.getExternalStorageDirectory());
        initView();
        initEvents();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        0);
            } else {

            }
        }
    }

    private void initEvents() {
        mListView = (ListView) findViewById(R.id.id_listview);
        mListView.setAdapter(new ArrayAdapter<String>(this, -1, mDatas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent
                            , false);
                }

                TextView tv = (TextView) convertView.findViewById(R.id.id_tv_title);
                tv.setText(getItem(position));
                return convertView;
            }
        });


        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;

                if (drawerView.getTag().equals("LEFT")) {
                    float leftScale = 1 - 0.3f * scale;

                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }
        });
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.id_left_menu_container);
        if (fragment == null) {
            fm.beginTransaction().add(R.id.id_left_menu_container, new MenuLeftFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.id_action_plugin_skinchange:
                SkinManager.getInstance().changeSkin(mSkinPkgPath, mSkinPlugPkg
                        , new ISkinChangingCallback() {
                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(MainActivity.this, "换肤失败"
                                        , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(MainActivity.this, "换肤成功"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.id_action_remove_any_skin:
                SkinManager.getInstance().removeAnySkin();
                break;
            case R.id.id_action_test_res:
                // TODO: 2020-05-04 方式一
                AssetManager assetManager = null;
                try {
                    assetManager = AssetManager.class.newInstance();
                    Method addAssetPath = assetManager.getClass().getMethod("addAssetPath"
                            , String.class);
                    addAssetPath.invoke(assetManager, mSkinPkgPath);

                    File file = new File(mSkinPkgPath);
                    AppLogger.e(file.exists() + "");
                    if (file.exists()) {
                        AppLogger.e(file.exists() + "" + mSkinPkgPath);
                    }
                    Resources superRes = getResources();
                    Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics()
                            , superRes.getConfiguration());

                    int mainBgId = mResources.getIdentifier("skin_main_bg", "drawable"
                            , mSkinPlugPkg);
                    findViewById(R.id.id_drawerLayout).setBackgroundDrawable(mResources.getDrawable(mainBgId));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // TODO: 2020-05-04 方式二
                //loadPlugin(mSkinPkgPath, mSkinPlugPkg);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadPlugin(String mSkinPkgPath, String mSkinPlugPkg) {
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath"
                    , String.class);
            addAssetPath.invoke(assetManager, mSkinPkgPath);

            File file = new File(mSkinPkgPath);
            AppLogger.e(file.exists() + "");
            if (file.exists()) {
                AppLogger.e(file.exists() + "" + mSkinPkgPath);
            }

            Resources superRes = getResources();
            Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics()
                    , superRes.getConfiguration());

            ResourceManager resourceManager = new ResourceManager(mResources, mSkinPlugPkg, null);
            Drawable drawable = resourceManager.getDrawableByName("skin_main_bg");
            if (drawable != null) {
                mDrawerLayout.setBackgroundDrawable(drawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
