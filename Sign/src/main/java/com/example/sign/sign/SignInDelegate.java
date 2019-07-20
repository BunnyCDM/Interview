package com.example.sign.sign;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sign.R;
import com.example.sign.database.UserProfile;
import com.example.sign.delegate.LatteDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mac on 2019/2/26.
 */

public class SignInDelegate extends LatteDelegate {

    private static final String TAG = SignInDelegate.class.getSimpleName();
    @BindView(R.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R.id.edit_sign_in_password)
    TextInputEditText mPassword = null;
    @BindView(R.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R.id.icon_sign_in_wechat)
    ImageView iconSignInWechat;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R.id.btn_sign_in)
    public void onClickSignIn() {
        if (checkForm()) {
            UserProfile userProfile = new UserProfile();
            userProfile.setCode(0);
            userProfile.setMessage("OK");
            UserProfile.DataBean dataBean = new UserProfile.DataBean();
            dataBean.setUserId(1);
            dataBean.setName("邦尼");
            dataBean.setAvatar("http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0");
            dataBean.setGender("男");
            dataBean.setAddress("安徽");
            userProfile.setData(dataBean);
            String response = userProfile.toString();
            Log.d(TAG, "onClickSignUp: response=" + response);
            SignHandler.onSignIn(response, mISignListener);
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private boolean checkForm() {
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @OnClick(R.id.tv_link_sign_up)
    public void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    @OnClick(R.id.icon_sign_in_wechat)
    public void onClickWeChat() {
        Toast.makeText(getContext(), "微信登录暂时还未实现（因需注册后的appid及appkey）", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
