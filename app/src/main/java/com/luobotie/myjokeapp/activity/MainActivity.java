package com.luobotie.myjokeapp.activity;

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
import com.luobotie.myjokeapp.R;
import com.luobotie.myjokeapp.fragments.FragmentComment;
import com.luobotie.myjokeapp.fragments.FragmentHome;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar mNavigationBar;
    private static final String TAG = "MainActivity";
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft;
    private Fragment fragment1, fragment2, fragment3, fragment4, fragment5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        mNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
        //初始化底部栏
        initNavigationBar();
        //用于避免fragment的重叠现象的发生
        if (savedInstanceState == null) {
            Log.d(TAG, "initView: 初始化fragments");
            initFragment();
            //开始事务 添加如下的fragments
            ft = fm.beginTransaction();
            ft.add(R.id.container, fragment1)
                    .add(R.id.container, fragment2)
                    .add(R.id.container, fragment3)
                    .add(R.id.container, fragment4)
                    .add(R.id.container, fragment5);
            hideAllFragments(ft);
            ft.show(fragment1).commit();
        } else {
            Log.d(TAG, "initView: 避免重叠从而加载旧fragments");
            //第一个加载
            ft = fm.beginTransaction();
            hideAllFragments(ft);
            ft.show(fragment1).commit();
        }
    }


    private void initFragment() {
        fragment1 = FragmentHome.newInstance(0);
        fragment2 = FragmentComment.newInstance();
        fragment3 = FragmentComment.newInstance();
        fragment4 = FragmentComment.newInstance();
        fragment5 = FragmentComment.newInstance();
    }

    private void initNavigationBar() {

        mNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        //页面点击切换监听
        mNavigationBar.setTabSelectedListener(this);
        mNavigationBar.setAutoHideEnabled(true);
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
                hideAllFragments(ft);
                ft.show(fragment1).commit();
                break;
            case 1:
                Log.d("MainActivity", "ft.replace: " + position + "\n");
                ft = fm.beginTransaction();
                hideAllFragments(ft);
                ft.show(fragment2).commit();
                break;
            case 2:
                ft = fm.beginTransaction();
                hideAllFragments(ft);
                ft.show(fragment3).commit();
                break;
            case 3:
                ft = fm.beginTransaction();
                hideAllFragments(ft);
                ft.show(fragment4).commit();
                break;
            case 4:
                ft = fm.beginTransaction();
                hideAllFragments(ft);
                ft.show(fragment5).commit();
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

    private void hideAllFragments(FragmentTransaction transaction) {
        transaction.hide(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment4)
                .hide(fragment5);
    }

}
