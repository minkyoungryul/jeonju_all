package com.example.minkr.jeonju_all.facility.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.facility.data.FacilityListData;
import com.example.minkr.jeonju_all.facility.presenter.PolicePresenter;
import com.example.minkr.jeonju_all.util.Logger;
import com.nhn.android.maps.NMapActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-07.
 */

public class PoliceActivity extends NMapActivity implements PoliceView {

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    PolicePresenter presenter;
    List<FacilityListData> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);
        ButterKnife.bind(this);

        presenter = new PolicePresenter();
        presenter.attachView(this);
        presenter.getPoliceList();

        init();
        setListener();
    }

    private void init() {
        tv_title.setText("지구대");
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
    public void getPoliceList(List<FacilityListData> facilityListData) {
        Logger.log("#22 data->"+facilityListData.toString());
        datas.addAll(facilityListData);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
