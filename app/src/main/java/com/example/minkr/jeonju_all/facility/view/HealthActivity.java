package com.example.minkr.jeonju_all.facility.view;

import android.content.Context;
import android.os.Bundle;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.facility.data.HealthListData;
import com.example.minkr.jeonju_all.facility.presenter.HealthPresenter;
import com.nhn.android.maps.NMapActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minkr on 2017-11-09.
 */

public class HealthActivity extends NMapActivity implements HealthView{

    HealthPresenter presenter;
    List<HealthListData> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_police);

        presenter = new HealthPresenter();
        presenter.attachView(this);
        presenter.getHealthList();

        init();
    }

    private void init() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void notConnectNetworking() {

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void getHealthList(List<HealthListData> healthListData) {
        datas.addAll(healthListData);
    }
}
