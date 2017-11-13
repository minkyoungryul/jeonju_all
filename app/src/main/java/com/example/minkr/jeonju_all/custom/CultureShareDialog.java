package com.example.minkr.jeonju_all.custom;

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

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.culture.data.CultureListData;
import com.example.minkr.jeonju_all.culture.view.CultureActivity;
import com.example.minkr.jeonju_all.util.Logger;
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

import butterknife.BindView;

/**
 * Created by Jun on 2017. 11. 3..
 */

public class CultureShareDialog extends Dialog {

    @BindView(R.id.ib_kakao)
    ImageButton ib_kakao;
    @BindView(R.id.ib_facebook)
    ImageButton ib_facebook;
    @BindView(R.id.ib_weblink)
    ImageButton ib_weblink;

    Context mContext;

    CultureListData data;
    private String url;

    public CultureShareDialog(@NonNull Context context, CultureListData data) {
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

        url = data.getUserHomepage();

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

        LocationTemplate params = LocationTemplate.newBuilder(data.getAddr(),
                ContentObject.newBuilder(data.getTitle(), data.getImg_url(),
                        LinkObject.newBuilder()
                                .setWebUrl(url)
                                .setMobileWebUrl(url)
                                .build())
                        .setDescrption(data.getAddr())
                        .build())
                .setAddressTitle(data.getAddr())
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
        ShareDialog shareDialog = new ShareDialog((CultureActivity) this.mContext);
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
