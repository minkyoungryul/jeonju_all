package com.example.minkr.jeonju_all.main.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.util.Logger;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.kakaolink.v2.model.ButtonObject;
import com.kakao.kakaolink.v2.model.ContentObject;
import com.kakao.kakaolink.v2.model.FeedTemplate;
import com.kakao.kakaolink.v2.model.LinkObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.util.Colors;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-10-24.
 */

public class MainSettingFrag extends Fragment {

    @BindView(R.id.rl_kakao_chatbot)
    RelativeLayout rl_kakao_chatbot;
    @BindView(R.id.ll_shareFriend)
    LinearLayout ll_shareFriend;

    @BindView(R.id.tv_version)
    TextView tv_version;

    @BindView(R.id.ll_open_source)
    LinearLayout ll_open_source;

    @BindView(R.id.ll_send_email)
    LinearLayout ll_send_email;

    @BindView(R.id.ll_intro)
    LinearLayout ll_intro;

    private String download_url = "http://www.google.com/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
        setListener();
    }

    private void init() {
        try {
            PackageInfo i = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            tv_version.setText("v " + i.versionName + ".0");
        } catch (PackageManager.NameNotFoundException e) {
        }

    }

    private void setListener() {
        ll_intro.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), IntroduceActivity.class);
            startActivity(intent);
        });

        ll_shareFriend.setOnClickListener(v -> {
            shareKakao();
        });
        rl_kakao_chatbot.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("https://pf.kakao.com/_DVxgDu");
            intent.setData(uri);
            startActivity(intent);

//            Intent launchIntent = getContext().getPackageManager().getLaunchIntentForPackage("com.kakao.kakaotalk.v2");
//            startActivity(launchIntent);
        });

        ll_open_source.setOnClickListener(v -> {
            Colors colors = new Colors(getContext().getResources().getColor(R.color.brown), getContext().getResources().getColor(R.color.brown));

            new LibsBuilder()
                    .withSortEnabled(false)
                    .withAutoDetect(true)
                    .withActivityStyle(Libs.ActivityStyle.LIGHT)
                    .withActivityColor(colors)
                    .withAboutIconShown(true)
                    .withAboutVersionShown(true)
                    .withActivityTitle("오픈소스 라이브러리")
                    .start(getContext());
        });


        ll_send_email.setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("plain/text");
            // email setting 배열로 해놔서 복수 발송 가능
            String[] address = {"minkr3321@naver.com", "rladudrb1200@naver.com", "blackbull8810@gmail.com"};
            email.putExtra(Intent.EXTRA_EMAIL, address);
            email.putExtra(Intent.EXTRA_SUBJECT, "전주의 모든 것 문의사항 입니다.");
            email.putExtra(Intent.EXTRA_TEXT, "내용을 입력해주세요.\n");
            startActivity(email);
        });
    }

    public void shareKakao() {

        FeedTemplate params = FeedTemplate
                .newBuilder(ContentObject.newBuilder("전주의 모든 것",
                        "http://tour.jeonju.go.kr/images/munhwa/sub/06img05.jpg",
                        LinkObject.newBuilder().setWebUrl(download_url)
                                .setMobileWebUrl(download_url).build())
                        .setDescrption("전주의 모든 것을 경험해보세요.")
                        .build())
                .addButton(new ButtonObject("앱 다운로드", LinkObject.newBuilder()
                        .setWebUrl(download_url)
                        .setMobileWebUrl(download_url)
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
        });
    }

}
