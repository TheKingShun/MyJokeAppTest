package com.luobotie.myjokeapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.luobotie.myjokeapp.fragments.FragmentComment;
import com.luobotie.myjokeapp.fragments.FragmentJoke;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;
    private Toolbar mToolbar;
    private BottomNavigationBar mNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mTabLayout = (TabLayout) findViewById(R.id.tl_tab);
        mViewPager = (ViewPager) findViewById(R.id.vp_content);

        mNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        mNavigationBar.setAutoHideEnabled(true);
        mNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mNavigationBar.setTabSelectedListener(this);
        mNavigationBar.addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "首页").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "想法").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "市场").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "通知").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "更多").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();



        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);

        initContent();
        initTab();

    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("笑话");
        tabIndicators.add("热门");
        tabIndicators.add("话题");

        tabFragments = new ArrayList<>();
        tabFragments.add(FragmentJoke.newInstance());
        tabFragments.add(FragmentComment.newInstance("2"));
        tabFragments.add(FragmentComment.newInstance("3"));

        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(contentAdapter);


    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
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
            return tabIndicators.get(position);
        }
    }

    /**
     * 初始化tab标签
     */
    private void initTab() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.colorGray),
                ContextCompat.getColor(this, R.color.colorWhite));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorWhite));
        //设置TabLayout的z轴高度
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
