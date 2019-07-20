package com.example.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mac on 2019/1/29.
 */

public class TopBar extends RelativeLayout {

    /**
     * 使用的控件
     */
    private Button leftButton, rightButton;
    private TextView tvTitle;


    /**
     * 控件的属性
     */
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private LayoutParams leftParams, rightParams, titleParams;

    public TopBar(final Context context, AttributeSet attrs) {
        super(context, attrs);

        //android中通过TypedArray获取我们自定义的属性,属性-值的映射
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText = ta.getString(R.styleable.Topbar_leftText);

        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText = ta.getString(R.styleable.Topbar_rightText);

        titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 0);
        titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor, 0);
        title = ta.getString(R.styleable.Topbar_title);

        //释放资源、释放缓存所引起的一些错误
        ta.recycle();

        //实例化控件
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        //设置控件属性
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setTextSize(titleTextSize);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);


        setBackgroundColor(0Xfff59563);//viewgroup的背景颜色

        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//宽高
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);//左对齐

        addView(leftButton, leftParams);// leftButton以leftParams添加viewgroup

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);

        addView(rightButton, rightParams);

        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);

        addView(tvTitle, titleParams);


        //接口回掉机制
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });

    }


    private topbarClickListener listener;

    public interface topbarClickListener {
        public void leftClick();

        public void rightClick();
    }

    public void setOnClickListener(topbarClickListener listener) {
        this.listener = listener;
    }

    public void setLetIsVisible(boolean flag) {
        if (flag) {
            leftButton.setVisibility(View.VISIBLE);
        } else {
            leftButton.setVisibility(View.GONE);
        }
    }

    public void setRightIsVisible(boolean flag) {
        if (flag) {
            rightButton.setVisibility(View.VISIBLE);
        } else {
            rightButton.setVisibility(View.GONE);
        }
    }

    public void setTitleIsVisible(boolean flag) {
        if (flag) {
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    public void setTopbarIsVisible(boolean flag) {
        TopBar topBar = (TopBar) findViewById(R.id.toolbar);
        if (flag) {
            topBar.setVisibility(View.VISIBLE);
        } else {
            topBar.setVisibility(View.GONE);
        }
    }

}
