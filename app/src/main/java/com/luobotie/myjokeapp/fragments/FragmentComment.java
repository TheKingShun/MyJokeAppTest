package com.luobotie.myjokeapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luobotie.myjokeapp.R;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/15.
 * 努力 加油 我是最棒的！2017
 */

public class FragmentComment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private String mPage;

    public static FragmentComment newInstance(String page) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, page);
        FragmentComment fragmentComment = new FragmentComment();
        fragmentComment.setArguments(args);
        return fragmentComment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getString(ARG_PAGE);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView textView ;

        View view = inflater.inflate(R.layout.fragment_comment,null,false);
        textView = (TextView) view.findViewById(R.id.tv_content);
        textView.setText("Fragment"+mPage);

        return view;
    }
}
