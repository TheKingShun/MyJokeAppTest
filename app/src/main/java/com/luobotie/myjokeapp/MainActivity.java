package com.luobotie.myjokeapp;

import android.graphics.ImageFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.luobotie.myjokeapp.fragments.FragmentComment;
import com.luobotie.myjokeapp.fragments.FragmentHome;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar mNavigationBar;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft;
    private Fragment fragment1, fragment2, fragment3, fragment4, fragment5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);


            initFragment();

        //初始化底部栏
        initNavigationBar();
        //开始事务
        ft = fm.beginTransaction();
        ft.add(R.id.container, fragment1)
                .add(R.id.container, fragment2)
                .add(R.id.container, fragment3)
                .add(R.id.container, fragment4)
                .add(R.id.container, fragment5).commit();
        //第一个加载
        ft = fm.beginTransaction();
        showFragmentHome(ft);


    }


    private void initFragment() {
        fragment1 = FragmentHome.newInstance(0);
        fragment2 = FragmentComment.newInstance("1");
        fragment3 = FragmentComment.newInstance("2");
        fragment4 = FragmentComment.newInstance("3");
        fragment5 = FragmentComment.newInstance("4");
    }

    private void initNavigationBar() {
        mNavigationBar.setAutoHideEnabled(true);
        mNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        //页面点击切换监听
        mNavigationBar.setTabSelectedListener(this);
        mNavigationBar.addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "首页").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "想法").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "市场").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "通知").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(android.R.drawable.ic_menu_manage, "更多").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {

        Log.d("MainActivity", "onTabSelected: " + position);

        switch (position) {
            case 0:
                ft = fm.beginTransaction();
                showFragmentHome(ft);
                break;
            case 1:
                Log.d("MainActivity", "ft.replace: " + position + "\n");
                ft = fm.beginTransaction();
                showFragmentIdea(ft);
                break;
            case 2:
                ft = fm.beginTransaction();
                showFragmentMarket(ft);
                break;
            case 3:
                ft = fm.beginTransaction();
                showFragmentNotification(ft);
                break;
            case 4:
                ft = fm.beginTransaction();
                showFragmentMore(ft);
                break;

        }
    }


    @Override
    public void onTabUnselected(int position) {
        Log.d("MainActivity", "onTabUnselected: ");

    }

    @Override
    public void onTabReselected(int position) {
        Log.d("MainActivity", "onTabReselected: ");

    }


    private void showFragmentMore(FragmentTransaction transaction) {
        transaction.show(fragment5)
                .hide(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment4)
                .commit();
    }

    private void showFragmentNotification(FragmentTransaction transaction) {
        transaction.show(fragment4)
                .hide(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment5)
                .commit();
    }

    private void showFragmentMarket(FragmentTransaction transaction) {
        transaction.show(fragment3)
                .hide(fragment1)
                .hide(fragment2)
                .hide(fragment4)
                .hide(fragment5)
                .commit();
    }

    private void showFragmentIdea(FragmentTransaction transaction) {
        transaction.show(fragment2)
                .hide(fragment1)
                .hide(fragment3)
                .hide(fragment4)
                .hide(fragment5)
                .commit();
    }


    private void showFragmentHome(FragmentTransaction transaction) {
        transaction.show(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment4)
                .hide(fragment5)
                .commit();
    }

}
