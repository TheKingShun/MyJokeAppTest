package com.luobotie.myjokeapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luobotie.myjokeapp.R;
import com.luobotie.myjokeapp.fragments.home.FragmentJoke;
import com.luobotie.myjokeapp.fragments.home.FragmentNews;

import java.util.ArrayList;
import java.util.List;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/16.
 * 努力 加油 我是最棒的！2017
 */

public class FragmentHome extends Fragment {

    public static final String ARG_KEY = "key";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPageAdapter contentAdapter;


    public static FragmentHome newInstance(int index) {

        Bundle args = new Bundle();
        args.putInt(ARG_KEY,index);

        FragmentHome fragment = new FragmentHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       int index =  getArguments().getInt(ARG_KEY,0);
        Log.d("FragmentHome", "onCreateView:getArguments "+index);

        View view = inflater.inflate(R.layout.fragment_home, null, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_tab);
        viewPager = (ViewPager) view.findViewById(R.id.vp_content);
        //配置tab
        initTabLayout();
        return view;
    }

    /**
     * 对tablayout进行细节操作
     */
    private void initTabLayout() {
        //导航栏菜单项
        tabIndicators = new ArrayList<>();
        tabIndicators.add("笑话");
        tabIndicators.add("热门");
        tabIndicators.add("话题");
        //导航栏 具体内容fragment
        tabFragments = new ArrayList<>();
        tabFragments.add(FragmentJoke.newInstance());
        tabFragments.add(FragmentNews.newInstance());
        tabFragments.add(FragmentComment.newInstance());
        //适配器
        contentAdapter = new ContentPageAdapter(getChildFragmentManager());
        //给viewPager设置适配器
        viewPager.setAdapter(contentAdapter);

        //设置模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.colorGray),
                ContextCompat.getColor(getContext(), R.color.colorWhite));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
        //设置TabLayout的z轴高度
        tabLayout.setupWithViewPager(viewPager);
    }


    class ContentPageAdapter extends FragmentPagerAdapter {

        public ContentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }


        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("FragmentHome", "getPageTitle: "+tabIndicators.get(position));
            return tabIndicators.get(position);
        }
    }
}
