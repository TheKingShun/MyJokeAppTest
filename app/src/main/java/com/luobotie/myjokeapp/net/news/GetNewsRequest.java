package com.luobotie.myjokeapp.net.news;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/18.
 * 努力 加油 我是最棒的！2017
 */


//http://toutiao-ali.juheapi.com/toutiao/index
public interface GetNewsRequest {
    @GET("/toutiao/index")
    Call<NewsBean> getNews(@Header("Authorization") String header, @QueryMap Map<String,String> param);
}
