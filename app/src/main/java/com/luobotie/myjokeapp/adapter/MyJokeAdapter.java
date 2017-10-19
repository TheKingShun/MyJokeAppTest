package com.luobotie.myjokeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luobotie.myjokeapp.R;

import java.util.List;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/15.
 * 努力 加油 我是最棒的！2017
 */

public class MyJokeAdapter extends RecyclerView.Adapter<MyJokeAdapter.MyJokeViewHolder> {

    public static final String TAG = "MyJokeAdapter";
    private List<String> datas;

    public MyJokeAdapter(List<String> datas) {
        this.datas = datas;
    }


    @Override
    public MyJokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyJokeViewHolder holder = new MyJokeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.joke_item, null, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyJokeViewHolder holder, int position) {
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
        }
    }

    public class MyJokeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvContent;

        public MyJokeViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_joke_content);
            tvContent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(getAdapterPosition());
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
