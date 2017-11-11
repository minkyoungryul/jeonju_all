package com.example.minkr.jeonju_all.main.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.minkr.jeonju_all.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-10-24.
 */

public class MainSettingFrag extends Fragment {

    @BindView(R.id.rl_kakao_chatbot)
    RelativeLayout rl_kakao_chatbot;

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
    }
}
