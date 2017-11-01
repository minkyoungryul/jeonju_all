package com.example.minkr.jeonju_all.parking.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.parking.presenter.ParkingPresenter;

/**
 * Created by minkr on 2017-10-22.
 */

public class ParkingActivity extends AppCompatActivity implements ParkingVIew{

    ParkingPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        presenter = new ParkingPresenter();
        presenter.attachView(this);

        presenter.getParkingList();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void notConnectNetworking() {

    }
}
