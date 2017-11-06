package com.example.minkr.jeonju_all.house.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.house.data.HouseListData;
import com.example.minkr.jeonju_all.util.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-01.
 */

public class HouseStoreInfoActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView mWebView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    HouseListData data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        data = (HouseListData) intent.getSerializableExtra("data");
        init();
        setListener();
    }

    private void init() {
        mWebView.getSettings().setJavaScriptEnabled(true);

        try {
            Logger.log("#33 homepage ->"+ URLEncoder.encode(data.getHomepage(), "EUC_KR") + ", "+ data.getHomepage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (data.getHomepage() == null || data.getHomepage().equals("")){
            Toast.makeText(this, "해당 홈페이지 정보가 없습니다.", Toast.LENGTH_SHORT);
        }else{
            mWebView.loadUrl(data.getHomepage());
        }
        mWebView.setWebViewClient(new WebViewClientClass());
        // WebViewClient 지정
    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());
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
    public void onBackPressed() {
        finish();
    }
}
