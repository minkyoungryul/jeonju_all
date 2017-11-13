package com.min.kr.jeonju_all.main.view;

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
import android.widget.Toast;

import com.min.kr.jeonju_all.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-10.
 */

public class BookmarkDetailActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView mWebView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    String url;
    String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        url = intent.getStringExtra("data");
        type = intent.getStringExtra("type");
        init();
        setListener();
    }

    private void init() {
        tv_title.setText(type);

        mWebView.getSettings().setJavaScriptEnabled(true);

        if (url == null || url.equals("")){
            Toast.makeText(this, "해당 홈페이지 정보가 없습니다.", Toast.LENGTH_SHORT);
        }else{
            mWebView.loadUrl(url);
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
