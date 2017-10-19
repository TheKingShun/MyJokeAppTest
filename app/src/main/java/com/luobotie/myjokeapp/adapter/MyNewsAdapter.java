package com.luobotie.myjokeapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luobotie.myjokeapp.R;
import com.luobotie.myjokeapp.net.news.NewsBean;

import java.net.URL;
import java.util.List;

import retrofit2.http.Url;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/19.
 * 努力 加油 我是最棒的！2017
 */

public class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.MyNewsViewHolder> {

    private MyNewsAdapter.onItemClickListener mListener;
    //数据信息传入到这里
    private List<NewsBean.ResultBean.DataBean> mDatas;
    private Context context;


    //构造方法中需要传入数据集 以及上下文
    public MyNewsAdapter(List<NewsBean.ResultBean.DataBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public MyNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyNewsViewHolder holder = new MyNewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, null, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyNewsViewHolder holder, int position) {

        NewsBean.ResultBean.DataBean dataBean = mDatas.get(position);

        holder.mNewsTitle.setText(dataBean.getTitle());
        holder.mNewsDate.setText(dataBean.getDate());
        holder.picOne.setVisibility(View.GONE);
        holder.picTwo.setVisibility(View.GONE);
        holder.picThree.setVisibility(View.GONE);
//        holder.picOne.setTag(dataBean.getPic_one());
//        holder.picTwo.setTag(dataBean.getPic_two());
//        holder.picThree.setTag(dataBean.getPic_three());

        if (dataBean.getPic_one() != null) {
            holder.picOne.setVisibility(View.VISIBLE);
            loadPic(Uri.parse(dataBean.getPic_one()), holder.picOne);

        }
        if (dataBean.getPic_two() != null) {
            holder.picTwo.setVisibility(View.VISIBLE);
            loadPic(Uri.parse(dataBean.getPic_two()), holder.picTwo);
        }
        if (dataBean.getPic_three() != null) {
            holder.picThree.setVisibility(View.VISIBLE);
            loadPic(Uri.parse(dataBean.getPic_three()), holder.picThree);
        }


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void swapDatas(List<NewsBean.ResultBean.DataBean> datas) {
        mDatas = datas;
        this.notifyDataSetChanged();
    }


    class MyNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mNewsTitle;
        private TextView mNewsDate;
        private ImageView picOne;
        private ImageView picTwo;
        private ImageView picThree;


        public MyNewsViewHolder(View itemView) {
            super(itemView);

            mNewsDate = (TextView) itemView.findViewById(R.id.tv_news_date);
            mNewsTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
            picOne = (ImageView) itemView.findViewById(R.id.iv_news_pic_one);
            picTwo = (ImageView) itemView.findViewById(R.id.iv_news_pic_two);
            picThree = (ImageView) itemView.findViewById(R.id.iv_news_pic_three);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListener.onClick(mDatas.get(getAdapterPosition()).getUrl());
        }
    }


    public interface onItemClickListener {
        void onClick(String uri);
    }

    public void setOnItemClickListener(MyNewsAdapter.onItemClickListener listener){
        mListener = listener;
    }

    private void loadPic(Uri uri, ImageView view) {
        Glide.with(context)
                .load(uri)
                .error(R.mipmap.error)
                .placeholder(R.mipmap.placeholder)
                .crossFade()
                .fallback(R.mipmap.ic_launcher)
                .into(view);
    }
}
