package com.example.custompullmenu;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.custompullmenu.adapter.OptionsAdapter;
import com.example.custompullmenu.db.AccountDao;
import com.example.custompullmenu.db.AccountBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userET;
    private CheckBox historyCB;
    private RelativeLayout pwdBottom;
    private EditText pwdET;
    private Button loginBtn;
    private AccountDao accountDao;

    //下拉框选项数据源
    ArrayList<AccountBean> datas = new ArrayList<AccountBean>();
    private List<AccountBean> historyList;
    private PopupWindow selectPopupWindow;
    private ListView listview;
    private OptionsAdapter optionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        userET = (EditText) findViewById(R.id.userET); //账号
        historyCB = (CheckBox) findViewById(R.id.historyCB); //用户登录历史
        pwdBottom = (RelativeLayout) findViewById(R.id.rl_bottom);//账号以下的部分
        pwdET = (EditText) findViewById(R.id.pwdET);  //密码
        loginBtn = (Button) findViewById(R.id.loginBtn); //登录按钮

        accountDao = new AccountDao();
    }

    private void initListener() {
        loginBtn.setOnClickListener(this);
    }

    private void initData() {
        //是否显示历史登录列表
        historyCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    initPopuWindow();//显示历史列表
                    if (historyList.size() == 0) {
                        pwdBottom.setVisibility(View.VISIBLE);
                    } else {
                        pwdBottom.setVisibility(View.GONE);
                    }
                } else {
                    selectPopupWindow.dismiss(); //隐藏列表
                    pwdBottom.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopuWindow() {
        initAddNum();
        //PopupWindow浮动下拉框布局
        View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.options, null);
        selectPopupWindow = new PopupWindow(view);
        selectPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        selectPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        listview = (ListView) view.findViewById(R.id.list);
        optionsAdapter = new OptionsAdapter(this, datas, accountDao);
        listview.setAdapter(optionsAdapter);
        selectPopupWindow.showAsDropDown(historyCB, 0, 0);
        selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phone = datas.get(position).getPhone();
                userET.setText(phone);
                selectPopupWindow.dismiss();
                historyCB.setChecked(false);
                pwdBottom.setVisibility(View.VISIBLE);
                optionsAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 登录列表数据
     * 初始化填充Adapter所用List数据
     */
    private void initAddNum() {
        datas.clear();
        historyList = (List<AccountBean>) accountDao.queryAll();
        Comparator<AccountBean> comparator = new Comparator<AccountBean>() {
            @Override
            public int compare(AccountBean t1, AccountBean t2) {
                if (Long.parseLong(t1.getTime() + "") < Long.parseLong(t2.getTime() + "")) {
                    return 1;
                }
                if (Long.parseLong(t1.getTime() + "") > Long.parseLong(t2.getTime() + "")) {
                    return -1;
                }
                return 1;
            }
        };
        Collections.sort(historyList, comparator);
        if (historyList.size() > 5) {
            historyList = historyList.subList(0, 5);
        }
        datas.addAll(historyList);

    }

    public void setVivi() {
        if (datas.size() == 0) {
            pwdBottom.setVisibility(View.VISIBLE);
            historyCB.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                if (TextUtils.isEmpty(userET.getText().toString()) || TextUtils.isEmpty(pwdET.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "账号或者密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    AccountBean accountBean = new AccountBean(userET.getText().toString(), "Tom", new Date().getTime());
                    accountDao.insert(accountBean);
                    startActivity(new Intent(LoginActivity.this, new SecondActivity().getClass()));
                }
                break;
        }
    }
}
