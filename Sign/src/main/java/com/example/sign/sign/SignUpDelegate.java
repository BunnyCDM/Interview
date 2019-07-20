package com.example.sign.sign;

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

import com.example.sign.R;
import com.example.sign.database.UserProfile;
import com.example.sign.delegate.LatteDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mac on 2019/2/26.
 */

public class SignUpDelegate extends LatteDelegate {

    private static final String TAG = SignUpDelegate.class.getSimpleName();
    @BindView(R.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;
    @BindView(R.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void onClickSignUp() {
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
            SignHandler.onSignUp(response, mISignListener);
        }

    }

    @OnClick(R.id.tv_link_sign_in)
    public void onClickLink() {
        getSupportDelegate().start(new SignInDelegate());
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private boolean checkForm() {
        final String name = mName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String rePassword = mRePassword.getText().toString().trim();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || mPassword.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

}