package com.luobotie.myjokeapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.luobotie.myjokeapp.GetRequest;
import com.luobotie.myjokeapp.JokeBean;
import com.luobotie.myjokeapp.MyAdapter;
import com.luobotie.myjokeapp.R;
import com.luobotie.myjokeapp.ToastUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/15.
 * 努力 加油 我是最棒的！2017
 */

public class FragmentJoke extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final int FLAG_FIRST_IN = 0;
    public static final int FLAG_IN = 1;
    private RecyclerView mRecyclerView;
    private TextView mErrorTips;
    private SwipeRefreshLayout mRefresh;
    public static final String BASE_URL = "http://japi.juhe.cn";
    public static final int PAGE_SIZE_DEFAULT = 8;
    public static final int PAGE_SIZE_MAX = 20;
    public static final String PARAMS_KEY = "61454b30735d4c1d36cb6be6a2912562";
    public static final int MESSAGE_ERROR_TIPS_INVISIBLE = 0;
    public static final int ERROR_TIPS_DELAY_MILLIS = 2500;
    private int page = 1;
    private int pagesize = PAGE_SIZE_DEFAULT;
    private static final String TAG = "FragmentJoke";
    private List<String> mDatas = new ArrayList<>();
    private MyAdapter adapter;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.arg1 == MESSAGE_ERROR_TIPS_INVISIBLE) {
                mErrorTips.setVisibility(View.INVISIBLE);
            }
            return true;
        }
    });

    public static FragmentJoke newInstance() {
        Bundle args = new Bundle();
        args.putString("123", "123");
        FragmentJoke fragmentJoke = new FragmentJoke();
        fragmentJoke.setArguments(args);
        return fragmentJoke;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FragmentJoke", "onCreate: ");
        initDatas();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, null, false);
        mErrorTips = (TextView) view.findViewById(R.id.tv_error_tips);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_joke);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        initViews();
        return view;
    }


    /**
     * 请求数据并更新在视图界面上
     */
    private void initDatas() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest getRequest = retrofit.create(GetRequest.class);

        //访问参数 用map形式表示
        Map<String, String> params = new TreeMap<>();
        params.put("page", String.valueOf(page));
        params.put("pagesize", String.valueOf(pagesize));
        params.put("key", PARAMS_KEY);

        //call方法 返回结果
        Call<JokeBean> call = getRequest.getJoke(params);
        call.enqueue(new Callback<JokeBean>() {
            @Override
            public void onResponse(Call<JokeBean> call, Response<JokeBean> response) {
                //将集合反转
                Collections.reverse(mDatas);
                List<JokeBean.ResultBean.DataBean> list = response.body().getResult().getData();
                for (JokeBean.ResultBean.DataBean dataBean : list) {
                    mDatas.add(dataBean.getContent());
                }
                //将数据与rv连接
                Collections.reverse(mDatas);
                Log.d(TAG, "onResponse: 全部的数据--------》" + mDatas.size());
                swapDatas();
                mRefresh.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<JokeBean> call, Throwable t) {
                sendErrorTips();
            }
        });
    }

    private void swapDatas() {

        //设置数据
        adapter = new MyAdapter(mDatas);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                ToastUtils.setShortToast(getContext(), String.valueOf(position));
            }
        });
        mRecyclerView.setAdapter(adapter);
    }


    private void initViews() {

        mRefresh.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mRefresh.setOnRefreshListener(this);

        mErrorTips.setVisibility(View.INVISIBLE);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swapDatas();

    }

    /**
     * 该方法用于发送错误消息
     */
    private void sendErrorTips() {
        //先让tips可见
        mErrorTips.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.error_tip_anim);
        mErrorTips.setAnimation(animation);
        mRefresh.setRefreshing(false);
        //1.5秒后将tiips隐藏
        mHandler.sendEmptyMessageDelayed(MESSAGE_ERROR_TIPS_INVISIBLE, ERROR_TIPS_DELAY_MILLIS);
    }

    @Override
    public void onRefresh() {
        //读取下一页数据
        page++;
        initDatas();
    }
}
