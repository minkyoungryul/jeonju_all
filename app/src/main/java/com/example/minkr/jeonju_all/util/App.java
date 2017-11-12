package com.example.minkr.jeonju_all.util;

import android.app.Application;

import com.tsengvn.typekit.Typekit;


/**
 * Created by minkr on 2017-11-11.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/BMHANNA_11yrs_ttf.ttf"));
    }
}
