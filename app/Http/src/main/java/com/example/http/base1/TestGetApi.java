package com.example.http.base1;

import com.example.baselibrary.utils.log.AppLogger;
import com.example.http.base1.api.ApiUtil;

import org.json.JSONObject;

/**
 * Created by mac on 2020-08-06.
 */
public class TestGetApi extends ApiUtil {

    //    public QuestionInfo mInfo;
    public String mResponse;

    @Override
    protected String getUrl() {
        return "https://appi.cardlan.com/bang/common/sysRealTimeStamp";
    }


    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        try {
            AppLogger.d("parseData(get): " + jsonObject.toString()+Thread.currentThread().getName());
//            JSONObject data =  jsonObject.optJSONObject("data");
//            JSONObject info =  data.optJSONObject("info");
//
//            mInfo = new Gson().fromJson(info.toString(),QuestionInfo.class);

            mResponse = jsonObject.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected boolean isBackInMainThread() {
        return true;
    }
}
