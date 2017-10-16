package com.luobotie.myjokeapp;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Policy;
import java.util.List;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/15.
 * 努力 加油 我是最棒的！2017
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static final String TAG = "MyAdapter";
    private List<String> datas;

    public MyAdapter(List<String> datas) {
        this.datas = datas;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, null, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvContent.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public  void swapData(List<String> datas){
        if (this.datas!=datas){
            this.datas = datas;
            Log.d(TAG, "swapData: 数据已经发生改变"+datas.size());
           this.notifyDataSetChanged();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_joke_content);
            tvContent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(getPosition());
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
