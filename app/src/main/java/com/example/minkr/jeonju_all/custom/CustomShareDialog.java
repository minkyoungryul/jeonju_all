package com.example.minkr.jeonju_all.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.util.Logger;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jun on 2017. 11. 3..
 */

public class CustomShareDialog extends Dialog {

    @BindView(R.id.ib_kakao)
    ImageButton ib_kakao;
    @BindView(R.id.ib_facebook)
    ImageButton ib_facebook;
    @BindView(R.id.ib_weblink)
    ImageButton ib_weblink;

    Context mContext;

    public CustomShareDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_share);

        init();
        setListener();

    }
    private void init() {
        ib_kakao = (ImageButton) findViewById(R.id.ib_kakao);
    }

    private void setListener() {
        ib_kakao.setOnClickListener(v -> {
            Logger.log("버튼누름");
            shareKakao();
        });
    }

    @OnClick({R.id.ib_kakao, R.id.ib_facebook, R.id.ib_weblink})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.ib_kakao :
                shareKakao();
                break;

            case R.id.ib_facebook :
                break;

            case R.id.ib_weblink :
                break;
        }
    }

    public void shareKakao() {
        Logger.log("버튼누름-------------");
        try {
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(mContext.getApplicationContext());
            final KakaoTalkLinkMessageBuilder kakaoBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            //메세지 추가
            kakaoBuilder.addText("카카오링크 테스트");

//            String url = "";
//            kakaoBuilder.addImage(url, 160, 160);
            kakaoBuilder.addAppLink("https://www.google.com");

            //앱실행버튼 추가
            kakaoBuilder.addAppButton("실행 버튼");

            //메세지 발송
            kakaoLink.sendMessage(kakaoBuilder, this.mContext);

        } catch (Exception e) {

            Logger.log("머고 안되노");

        }
    }


}
