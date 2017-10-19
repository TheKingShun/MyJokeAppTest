package com.luobotie.myjokeapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.luobotie.myjokeapp.R;
import com.luobotie.myjokeapp.fragments.home.FragmentNews;

public class NewsActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initViews();
        String uri = getIntent().getStringExtra(FragmentNews.INTENT_KEY_URI);
        mWebView.loadUrl(uri);
    }

    private void initViews() {
        mWebView = (WebView) findViewById(R.id.wv_news);
    }
}
