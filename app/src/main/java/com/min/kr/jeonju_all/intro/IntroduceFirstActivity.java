package com.min.kr.jeonju_all.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-14.
 */

public class IntroduceFirstActivity extends AppCompatActivity{

    @BindView(R.id.btn_close)
    Button btn_close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_first);
        ButterKnife.bind(this);

        setListener();
    }

    private void setListener() {
        btn_close.setOnClickListener(v->{
            Intent intent = new Intent(IntroduceFirstActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
