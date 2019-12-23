package com.zcc.frame.http;

import com.zcc.frame.activity.ScrollActivity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    //baseUrl
    String BASE_URL = "http://backend.paas.env";

    //加载天气
    @GET("adat/sk/{id}")
    Observable<ScrollActivity.Topic> loadDataByRetrofitRxJava(@Path("id") String id);
}
