package com.example.minkr.jeonju_all.kindFood.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;
import com.example.minkr.jeonju_all.util.Variable;
import com.tsengvn.typekit.TypekitContextWrapper;

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

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    String storeId;
    int data_map = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        storeId = intent.getStringExtra("storeId");
        data_map = intent.getIntExtra("datas",0);
        init();
        setListener();
    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());
    }

    private void init() {

        mWebView.getSettings().setJavaScriptEnabled(true);

        if (data_map == 0){
            mWebView.loadUrl(Variable._NAVER_STORE_INFO_URL+storeId);
        }else{
            mWebView.loadUrl(Variable._NAVER_STORE_INFO_URL+data_map);
        }
        //mWebView.loadUrl("https://store.naver.com/restaurants/detail?id="+data.getStoreId());
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
