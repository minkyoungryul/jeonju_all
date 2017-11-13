package com.example.minkr.jeonju_all.intro;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.minkr.jeonju_all.main.MainActivity;
import com.example.minkr.jeonju_all.R;
import com.facebook.stetho.Stetho;

/**
 * Created by minkr on 2017-10-22.
 */

public class IntroActivity extends AppCompatActivity{
    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);


        videoView = (VideoView) findViewById(R.id.introvideo);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.intro2));
        videoView.requestFocus();
        videoView.start();

        /*
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
        */

        Stetho.initializeWithDefaults(this);


        Handler mHanlder = new Handler();
        mHanlder.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }

}
