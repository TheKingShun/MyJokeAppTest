package com.luobotie.myjokeapp.fragments.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luobotie.myjokeapp.R;
import com.luobotie.myjokeapp.activity.NewsActivity;
import com.luobotie.myjokeapp.adapter.MyNewsAdapter;
import com.luobotie.myjokeapp.net.news.GetNewsRequest;
import com.luobotie.myjokeapp.net.news.NewsBean;
import com.luobotie.myjokeapp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/18.
 * 努力 加油 我是最棒的！2017
 */

public class FragmentNews extends Fragment implements MyNewsAdapter.onItemClickListener {


    public static final String TAG = "FragmentNews";
    public static final String INTENT_KEY_URI = "uri";
    private StringBuilder sb = new StringBuilder();
    public static final String ARG_KEY = "key";
    public static final int KEY_VALUE = 0;
    public static final String BASE_URL = "http://toutiao-ali.juheapi.com";
    public static final String PARAMS_KEY_AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_VALUE = "APPCODE 0d4ed82049a247bab552a81b0eda9d11";
    public static final String PARAMS_KEY_TYPE = "type";
    public static final String TYPE_VALUE = "top";
    private RecyclerView mRecyclerView;
    //news的数据适配器adapter
    private MyNewsAdapter adapter ;
    private List<NewsBean.ResultBean.DataBean> datas = new ArrayList<>();

    public static FragmentNews newInstance() {

        Bundle args = new Bundle();
        args.putInt(ARG_KEY, KEY_VALUE);
        FragmentNews fragment = new FragmentNews();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //请求一次数据
        initData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_news);
        initViews();
        return view;
    }

    private void initViews() {
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyNewsAdapter(datas,getContext());
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }


    /**
     * 初始化信息源
     */
    private void initData() {


        Map<String, String> params = new HashMap<>();
        params.put(PARAMS_KEY_TYPE, TYPE_VALUE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetNewsRequest request = retrofit.create(GetNewsRequest.class);
        Call<NewsBean> call = request.getNews(AUTHORIZATION_VALUE, params);
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                //请求结果的保存
                datas = response.body().getResult().getData();
                Log.d(TAG, "onResponse: datas结果长度"+datas.toArray().length);
                adapter.swapDatas(datas);

            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                ToastUtils.setShortToast(getContext(),"加载错误");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onClick(String uri) {
        Intent intentToNewsDetail = new Intent(getContext(), NewsActivity.class);
        intentToNewsDetail.putExtra(INTENT_KEY_URI,uri);
        startActivity(intentToNewsDetail);
    }
}
