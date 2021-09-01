package com.example.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baselibrary.utils.log.AppLogger;

/**
 * Created by mac on 2020-07-06.
 * <p>
 * 在Action Bar上添加按钮
 */
public class MainActivity01 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);


        // TODO: 2020-07-06  这网站可以可以一键生成：Android Action Bar Style Generator
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (int i = 0; i < 3; i++) {
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText("Tab" + i);
            tab.setTabListener(new ActionBar.TabListener() {
                @Override
                public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                    AppLogger.d("onTabSelected: ");
                }

                @Override
                public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                    AppLogger.d("onTabUnselected: ");
                }

                @Override
                public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                    AppLogger.d("onTabReselected: ");
                }
            });
            actionBar.addTab(tab);
        }

        //返回箭头（默认不显示）
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 显示标题
        actionBar.setDisplayShowTitleEnabled(false);

        // TODO: 2020-07-06 使用自定义视图 ，显示标题设置false

    }

    /**
     * 显示自定义视图01
     *
     * @return
     */
    private boolean initCustomActionBar01() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return false;
        }
        //显示自定义视图
        actionBar.setDisplayShowCustomEnabled(true);
        TextView textView = new TextView(this);

        textView.setText("asjdjasopj");

        textView.setTextSize(15);

        textView.setTextColor(0xffffffff);

        LinearLayout actionbarLayout = new LinearLayout(this);

        actionBar.setCustomView(actionbarLayout, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT));

        ActionBar.LayoutParams mP = (ActionBar.LayoutParams) actionbarLayout

                .getLayoutParams();

        mP.gravity = mP.gravity & ~Gravity.HORIZONTAL_GRAVITY_MASK | Gravity.CENTER_HORIZONTAL;

        actionbarLayout.addView(textView);

        actionBar.setCustomView(actionbarLayout, mP);
        return true;
    }

    /**
     * 显示自定义视图02
     *
     * @return
     */
    private boolean initCustomActionBar02() {
        ActionBar mActionbar = getSupportActionBar();
        if (mActionbar == null) {
            return false;
        }
        mActionbar.setDisplayShowCustomEnabled(true);
        mActionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionbar.setCustomView(R.layout.top_back_center_bar);
        TextView tvTitle = mActionbar.getCustomView().findViewById(R.id.tv_tbb_title);
        tvTitle.setText("originalTitle");
        mActionbar.getCustomView().findViewById(R.id.iv_tbb_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return true;
    }


    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem shareItem = menu.findItem(R.id.menu_share);
        // TODO: 2020-07-06 切记要与menu中使用一致， https://my.oschina.net/neumeng/blog/744018
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        mShareActionProvider.setShareIntent(getDefaultIntent());
        return true;
    }

    private ShareActionProvider mShareActionProvider;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_always:
                AppLogger.d("onOptionsItemSelected: menu_always");
                break;


            case R.id.menu_ifRoom:
                AppLogger.d("onOptionsItemSelected: menu_ifRoom");
                break;

            case R.id.menu_never:
                AppLogger.d("onOptionsItemSelected: menu_never");
                break;


            case R.id.menu_withText:
                AppLogger.d("onOptionsItemSelected: menu_withText");
                break;

            case R.id.menu_ifRoom_withText:
                AppLogger.d("onOptionsItemSelected: menu_ifRoom_withText");
                break;

            case android.R.id.home:
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }


}
