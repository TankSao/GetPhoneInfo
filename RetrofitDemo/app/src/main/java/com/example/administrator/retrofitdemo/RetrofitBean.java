package com.example.administrator.retrofitdemo;

import com.google.gson.Gson;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/11/15.
 */

public class RetrofitBean {
    private static RetrofitApi api;
    private static String BASE_URL = "http://apis.juhe.cn/";
    public static RetrofitApi getApi() {
        api = null;
        //初始化retrofit框架
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BASE_URL).build();

        //读取接口上面的参数
        api = build.create(RetrofitApi.class);
        return api;
    }
}
