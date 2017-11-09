package com.example.minkr.jeonju_all.facility.view;

import android.content.Context;
import android.os.Bundle;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.facility.data.HospitalListData;
import com.example.minkr.jeonju_all.facility.presenter.MedicinePresenter;
import com.nhn.android.maps.NMapActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minkr on 2017-11-09.
 */

public class MedicineActivity extends NMapActivity implements MedicineView {

    MedicinePresenter presenter;
    List<HospitalListData> medicine_datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_police);

        presenter = new MedicinePresenter();
        presenter.attachView(this);
        presenter.getMedicineList();

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
    public void getMedicineList(List<HospitalListData> hospitalListData) {
        medicine_datas.addAll(hospitalListData);
    }
}
