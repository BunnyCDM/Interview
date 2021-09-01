package com.example.http.base1.response;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by mac on 2020-08-06.
 */
public abstract class OkHttpCallback implements Callback {

    protected Handler mOkHandler = null;

    protected boolean isPostMainThread() {
        return true;
    }

    public final Handler getOkHandler() {
        return mOkHandler = new Handler(Looper.getMainLooper());
    }

    public abstract void onSuccess(final Call call, JSONObject response);

    protected final void onSuccess(final Call call, Response response) throws Throwable {
        String responseStr = response.body().string().trim();
        JSONObject responseObject = (JSONObject) new JSONTokener(responseStr).nextValue();
        if (responseObject != null) {
            onSuccessRequest(call, responseObject);
        } else {
            onFailureRequest(call);
        }

    }

    public void onSuccessRequest(final Call call, final JSONObject responseObject) {
        if (isPostMainThread()) {
            getOkHandler().post(new Runnable() {
                @Override
                public void run() {
                    onSuccess(call, responseObject);
                }
            });
        } else {
            onSuccess(call, responseObject);
        }
    }

    public void onFailureRequest(final Call call) {
        if (isPostMainThread()) {
            getOkHandler().post(new Runnable() {
                @Override
                public void run() {
                    onFailure(call, null);
                }
            });
        } else {
            onFailure(call, null);
        }
    }

    @Override
    public final void onResponse(Call call, Response response) {
        if (response != null) {
            try {
                if (response.isSuccessful()) {
                    onSuccess(call, response);
                } else {
                    onFailureRequest(call);
                }
            } catch (Throwable e) {
                onFailureRequest(call);
            }
        } else {
            onFailureRequest(call);
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }
}
