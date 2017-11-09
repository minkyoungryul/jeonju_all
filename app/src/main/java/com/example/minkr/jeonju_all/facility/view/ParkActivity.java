package com.example.minkr.jeonju_all.facility.view;

import android.content.Context;
import android.os.Bundle;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.facility.data.ParkListData;
import com.example.minkr.jeonju_all.facility.presenter.ParkPresenter;
import com.nhn.android.maps.NMapActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minkr on 2017-11-09.
 */

public class ParkActivity extends NMapActivity implements ParkView{

    List<ParkListData> datas = new ArrayList<>();
    ParkPresenter presenter;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_police);

        presenter = new ParkPresenter();
        presenter.attachView(this);
        presenter.getParkList();

        init();
    }

    private void init() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void notConnectNetworking() {

    }

    @Override
    public void getParkList(List<ParkListData> parkListData) {
        datas.addAll(parkListData);
    }
}