package com.luobotie.myjokeapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/15.
 * 努力 加油 我是最棒的！2017
 */

public interface GetRequest {
    @GET("/joke/content/text.from?")
    Call<JokeBean> getJoke(@QueryMap Map<String,String> params);
}
