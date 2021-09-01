package com.example.http.base1;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.http.base1.api.ApiUtil;

import org.json.JSONObject;

/**
 * Created by mac on 2020-08-06.
 */
public class TestPostApi extends ApiUtil {

    public TestPostApi(String name) {
        addParam("name", name);
    }

    @Override
    protected String getUrl() {
        return "https://appi.cardlan.com/bang/common/sysRealTimeStamp";
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        AppLogger.d("parseData(post): " + jsonObject.toString()+Thread.currentThread().getName());
    }

    @Override
    protected boolean isBackInMainThread() {
        return false;
    }
}
