package com.example.minkr.jeonju_all.facility.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageButton;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.facility.data.HospitalListData;
import com.example.minkr.jeonju_all.facility.presenter.HospitalPresenter;
import com.example.minkr.jeonju_all.facility.presenter.PolicePresenter;
import com.nhn.android.maps.NMapActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-07.
 */

public class HospitalActivity extends NMapActivity implements HospitalView {

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    HospitalPresenter presenter;
    List<HospitalListData> all_datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
        ButterKnife.bind(this);

        presenter = new HospitalPresenter();
        presenter.attachView(this);
        presenter.getHospitalTotalList();

        init();
        setListener();
    }

    private void init() {

    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());
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
    public void getHospitalTotalList(List<HospitalListData> hospitalListData) {
        all_datas.addAll(hospitalListData);
    }
}
