package com.example.administrator.retrofitdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by Administrator on 2018/11/15.
 */

public interface RetrofitApi {
    @GET("mobile/get?")
    Call<ResponseBody> getPhone(@Query("phone") String phone,@Query("key") String key);
}
