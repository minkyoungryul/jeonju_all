package com.example.minkr.jeonju_all.kindFood.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-01.
 */

public class FoodStoreInfoActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView mWebView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    KindFoodListData data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        data = (KindFoodListData) intent.getSerializableExtra("data");
        init();
    }

    private void init() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://store.naver.com/restaurants/detail?id="+data.getStoreId());
//        http://map.naver.com/local/siteview.nhn?code=17115770
        mWebView.setWebViewClient(new WebViewClientClass());
        // WebViewClient 지정
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
