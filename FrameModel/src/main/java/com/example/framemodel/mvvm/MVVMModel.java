package com.example.framemodel.mvvm;

import com.example.framemodel.Account;
import com.example.framemodel.callback.MCallback;

import java.util.Random;

/**
 * Created by mac on 2020-03-28.
 */
public class MVVMModel {


    //模拟查询账号数据
    public void getAccountData(String accountName, MCallback callback) {

        Random random=new Random();
        boolean isSuccess=random.nextBoolean();
        if(isSuccess){
            Account account=new Account(accountName,100);
            callback.onSuccess(account);
        }else {
            callback.onFailed();
        }

    }



}
