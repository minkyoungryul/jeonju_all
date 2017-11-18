package com.min.kr.jeonju_all.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.kindFood.data.KindFoodListData;
import com.min.kr.jeonju_all.kindFood.view.KindFoodActivity;
import com.min.kr.jeonju_all.util.Logger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.kakaolink.v2.model.ButtonObject;
import com.kakao.kakaolink.v2.model.ContentObject;
import com.kakao.kakaolink.v2.model.LinkObject;
import com.kakao.kakaolink.v2.model.LocationTemplate;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.min.kr.jeonju_all.util.Variable;

import butterknife.BindView;

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

    KindFoodListData data;
    private String url;

    public CustomShareDialog(@NonNull Context context, KindFoodListData data) {
        super(context);
        mContext = context;
        this.data = data;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_share);

        Logger.log("data ->"+data.toString());
        init();
        setListener();

        url = "https://store.naver.com/restaurants/detail?id="+data.getStoreId();

    }
    private void init() {
        ib_kakao = (ImageButton) findViewById(R.id.ib_kakao);
        ib_facebook = (ImageButton) findViewById(R.id.ib_facebook);
        ib_weblink = (ImageButton) findViewById(R.id.ib_weblink);
    }

    private void setListener() {
        ib_kakao.setOnClickListener(v -> {
            shareKakao();
            //getKeyHash(mContext);
        });
        ib_facebook.setOnClickListener(v -> {
            shareFacebook();
        });
        ib_weblink.setOnClickListener(v -> {
            copyToClipboard(this.getContext(), url);
        });
    }

    public void shareKakao() {

        /*FeedTemplate params = FeedTemplate
                .newBuilder(ContentObject.newBuilder(data.getName(),
                        data.getImg_url(),
                        LinkObject.newBuilder().setWebUrl(url)
                                .setMobileWebUrl(url).build())
                        .setDescrption(data.getAddress())
                        .build())
                *//*.setSocial(SocialObject.newBuilder().setLikeCount(10).setCommentCount(20)
                        .setSharedCount(30).setViewCount(40).build())*//*
                *//*.addButton(new ButtonObject("웹에서 보기", LinkObject.newBuilder()
                        .setWebUrl(url)
                        .setMobileWebUrl(url)
                        .build()))*//*
                .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                        .setWebUrl(url)
                        .setMobileWebUrl(url)
                        .setAndroidExecutionParams("key1=value1")
                        .setIosExecutionParams("key1=value1")
                        .build()))
                .build();

        KakaoLinkService.getInstance().sendDefault(this.getContext(), params, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.log(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {

            }
        });*/
        LocationTemplate params = LocationTemplate.newBuilder(data.getAddress(),
                ContentObject.newBuilder(data.getName(), data.getImg_url(),
                        LinkObject.newBuilder()
                                .setWebUrl(url)
                                .setMobileWebUrl(url)
                                .build())
                        .setDescrption(data.getAddress())
                        .build())
                .setAddressTitle(data.getName())
                .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                        .setWebUrl(Variable._GOOGLEPLAY_DOWNLOAD_URL)
                        .setMobileWebUrl(Variable._GOOGLEPLAY_DOWNLOAD_URL)
                        .setAndroidExecutionParams("key1=value1")
                        .setIosExecutionParams("key1=value1")
                        .build()))
                .build();

        KakaoLinkService.getInstance().sendDefault(this.getContext(), params, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.log(errorResult.toString());
            }
            @Override
            public void onSuccess(KakaoLinkResponse result) { }
        });
    }

    public void shareFacebook(){

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("페이스북 공유 링크입니다.")
                .setImageUrl(Uri.parse(data.getImg_url()))
                .setContentUrl(Uri.parse(url))
                //.setContentDescription(data.getName(),data.getAddress(),data.getPrice(),data.getFoodName())
                //.setContentDescription("1,2,3,4")
                .build();
        ShareDialog shareDialog = new ShareDialog((KindFoodActivity) this.mContext);
        shareDialog.show(content, ShareDialog.Mode.FEED);
    }

    public void copyToClipboard(Context context , String content){
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(content);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("label", content);
            clipboard.setPrimaryClip(clip);
        }

        Toast.makeText(context , "클립보드에 복사되었습니다." , Toast.LENGTH_SHORT).show();
    }
}
