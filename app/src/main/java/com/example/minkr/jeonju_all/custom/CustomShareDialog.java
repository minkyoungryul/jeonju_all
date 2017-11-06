package com.example.minkr.jeonju_all.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;
import com.example.minkr.jeonju_all.util.Logger;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.kakaolink.v2.model.ButtonObject;
import com.kakao.kakaolink.v2.model.ContentObject;
import com.kakao.kakaolink.v2.model.FeedTemplate;
import com.kakao.kakaolink.v2.model.LinkObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;

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

        });
        ib_weblink.setOnClickListener(v -> {
            copyToClipboard(this.getContext(), "jeonju_all");
        });
    }

    public void shareKakao() {

        FeedTemplate params = FeedTemplate
                .newBuilder(ContentObject.newBuilder(data.getName(),
                        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20150727_135%2Fmaxddong00_14379825629150FDh0_PNG%2F%25B8%25B8%25B3%25AA%25BA%25B0%25B9%25CC_%25B5%25B7%25B0%25A1%25BD%25BA_%25BB%25E7%25B6%25FB%25B5%25B5_%25C0%25CC%25B7%25B1_%25BB%25E7%25B6%25FB%25C0%25CC_%25BE%25F8%25B3%25D7%25BF%25E4_%25A4%25BE9.png&type=f420_312&quality=95&autoRotate=true",
                        LinkObject.newBuilder().setWebUrl(url)
                                .setMobileWebUrl(url).build())
                        .setDescrption(data.getAddress())
                        .build())
                /*.setSocial(SocialObject.newBuilder().setLikeCount(10).setCommentCount(20)
                        .setSharedCount(30).setViewCount(40).build())*/
                .addButton(new ButtonObject("웹에서 보기", LinkObject.newBuilder()
                        .setWebUrl(url)
                        .setMobileWebUrl(url)
                        .build()))
                .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                        .setWebUrl(url)
                        .setMobileWebUrl(url)
                        .setAndroidExecutionParams("key1=value1")
                        /*.setIosExecutionParams("key1=value1")*/
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
        });

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
