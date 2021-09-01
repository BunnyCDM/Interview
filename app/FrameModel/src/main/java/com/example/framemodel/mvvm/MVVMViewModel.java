package com.example.framemodel.mvvm;

import android.app.Application;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.example.framemodel.Account;
import com.example.framemodel.callback.MCallback;
import com.example.framemodel.databinding.ActivityMvvmBinding;


/**
 * Created by mac on 2020-03-28.
 */
public class MVVMViewModel extends BaseObservable {


    private MVVMModel mvvmModel;
    private ActivityMvvmBinding binding;
    private String result;
    private String userInput;

    /**
     * 一般要传入Application对象，方便在ViewModel中使用Application，譬如sp需要使用
     * @param application
     */
    public MVVMViewModel(Application application){
        mvvmModel=new MVVMModel();
    }

    public MVVMViewModel(Application application, ActivityMvvmBinding binding){
        mvvmModel=new MVVMModel();
        this.binding=binding;
    }

    public void getData(View view){
        //String userInput=binding.etAccount.getText().toString();
        mvvmModel.getAccountData(userInput, new MCallback() {
            @Override
            public void onSuccess(Account account) {
                String info=account.getName()+"|"+account.getLevel();
                setResult(info);

            }

            @Override
            public void onFailed() {
                setResult("获取数据失败");
            }
        });
    }

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(com.example.framemodel.BR.result);
    }

    @Bindable
    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        notifyPropertyChanged(com.example.framemodel.BR.userInput);
    }
}
