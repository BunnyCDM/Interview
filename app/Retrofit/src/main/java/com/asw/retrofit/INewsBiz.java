package com.asw.retrofit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by mac on 2017/4/14.
 */

public interface INewsBiz {
    /**
     * 通过@GET注解标记位get请求  @GET中填写baseUrl根路径后的路径
     * 访问的网络地址由根路径和@GET注解后的路径连接组成
     */

    //案例一：这种是将根路径后面的路径整个放入了get中进行拼接，相对比较简单，后期和根url拼接
//    @GET("api/GetFeeds?column=0&PageSize=20&pageIndex=1&val=100511D3BE5301280E0992C73A9DEC41")
    @GET("20130606/1284768.shtml")
    //此处的返回值可以自行设置，这里我就简单的让他返回请求体的内容：
    Call<ResponseBody> getResonseBody();

//    @GET("api/GetFeeds?column=0&PageSize=20&pageIndex=1&val=100511D3BE5301280E0992C73A9DEC41")
    @GET("20130606/1284768.shtml")
    //案例二：此处的返回值我让他返回的是String类型，具体返回逻辑我一会儿在主方法中创建：
    Call<String> getJsonString();

    //案例三：使用的是茶叶品牌的url,此处将根目录以后的部分进行了截取，但是返回的是一个Tea对象的集合,至于tea对象的生成大家一定都会使用Gson吧，这个我就不细说了。
//    @GET("category?app_id=161")
    @GET("产品名称-12")
    Call<List<Tea>> getTeas();

    //案例四：此处使用的是同案例一，但是path路径不确定的情况情况下，使用{type}代替，当做抽象方法中的一个参数进行接收，
    @GET("20130606/{type}?1284768.shtml")
    //这里的path就是你要传入的type类型，这里@path“tyle”名称一定要和@get();中的type同名，
    Call<NewsInfo> getNewsInfo(@Path("type") String type);//String type="GetFeeds"

    //--------
    //案例五：有一个键值对的情况，如果不确定时，需要使用此方法，使用@query（键名）将你的值输入:
    @GET("category")//category?app_id=161
    Call<List<Tea>> getTeaByNetWork(@Query("app_id") int id);

    //案例六：如果键值对是很多的情况下，案例五很明先无法满足需要，这时候需要使用@querymap接收一个map集合来进行拼凑。
    @GET("api/GetFeeds")
    Call<NewsInfo> getInfoByNetWork(@QueryMap Map<String, String> map);

    //案例七：你也可以使用多种方式拼凑结合：使用{}，加上参数的拼接等，都是可以灵活运行
    @GET("api/{type}")
    Call<NewsInfo> getInfo(@Path("type") String type, @QueryMap Map<String, String> map);


    //案例八：网络下载图片，
    @GET("img")
    Call<ResponseBody> downLoadImage();

    //post形式提交表单数据  登录 注册，post需要服务器，这里无法进行演示，但是代码有，需要的话可以从自己的服务器中进行验证，
    //@post注解说明采用post提交的形式 @Field("向服务器提交的key")
    @POST("LoginServlet")
    @FormUrlEncoded
    Call<String> login(@Field("username") String userName,
                       @Field("password") String password);


    //上传单个文件 @Field和@Part 客户端向服务端携带参数发起请求
    //@Part 可携带的参数类型更多样 数据流等
    @Multipart
    @POST("UpLoadAction")
    Call<ResponseBody> upLoadFile(@Part("img") RequestBody body);


    //可以上传表单中所有的数据  表单文本+附件
    @POST("UpLoadAction")
    Call<ResponseBody> upLoad(@Body() MultipartBody body);

}
