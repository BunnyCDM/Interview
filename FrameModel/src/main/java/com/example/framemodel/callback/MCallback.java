package com.example.framemodel.callback;

import com.example.framemodel.Account;

/**
 * Created by mac on 2020-03-28.
 */
public interface MCallback {


    void onSuccess(Account account);

    void onFailed();
}
