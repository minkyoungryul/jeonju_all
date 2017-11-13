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

    @BindView(R.id.tv_version)
    TextView tv_version;

    @BindView(R.id.ll_open_source)
    LinearLayout ll_open_source;

    @BindView(R.id.ll_info)
    LinearLayout ll_info;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_setting, container, false);
        ButterKnife.bind(this,view);
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
            tv_version.setText("v "+i.versionName+".0");
        } catch(PackageManager.NameNotFoundException e) { }

    }

    private void setListener() {
        rl_kakao_chatbot.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("https://pf.kakao.com/_DVxgDu");
            intent.setData(uri);
            startActivity(intent);

//            Intent launchIntent = getContext().getPackageManager().getLaunchIntentForPackage("com.kakao.kakaotalk.v2");
//            startActivity(launchIntent);
        });

        ll_open_source.setOnClickListener(v->{
            Colors colors = new Colors(getContext().getResources().getColor(R.color.brown),getContext().getResources().getColor(R.color.brown));

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

        ll_info.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),SettingInfo.class);
            startActivity(intent);
        });
    }
}
