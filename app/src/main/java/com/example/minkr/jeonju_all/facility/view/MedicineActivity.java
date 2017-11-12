package com.example.minkr.jeonju_all.facility.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.facility.data.HospitalListData;
import com.example.minkr.jeonju_all.facility.presenter.MedicinePresenter;
import com.nhn.android.maps.NMapActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-09.
 */

public class MedicineActivity extends NMapActivity implements MedicineView {

    MedicinePresenter presenter;
    List<HospitalListData> medicine_datas = new ArrayList<>();

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_police);
        ButterKnife.bind(this);

        presenter = new MedicinePresenter();
        presenter.attachView(this);
        presenter.getMedicineList();

        init();
        setListener();
    }

    private void init() {
        tv_title.setText("약국");
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
    public void getMedicineList(List<HospitalListData> hospitalListData) {
        medicine_datas.addAll(hospitalListData);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
