package com.example.minkr.jeonju_all.facility.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.facility.data.FacilityListData;
import com.example.minkr.jeonju_all.facility.data.HospitalListData;
import com.example.minkr.jeonju_all.facility.data.ParkListData;
import com.example.minkr.jeonju_all.facility.presenter.MedicinePresenter;
import com.example.minkr.jeonju_all.facility.presenter.ParkPresenter;
import com.example.minkr.jeonju_all.facility.presenter.PolicePresenter;
import com.example.minkr.jeonju_all.parking.data.ParkingListData;
import com.example.minkr.jeonju_all.parking.presenter.ParkingPresenter;
import com.example.minkr.jeonju_all.parking.view.ParkingActivity;
import com.example.minkr.jeonju_all.parking.view.ParkingMapActivity;
import com.example.minkr.jeonju_all.parking.view.ParkingVIew;
import com.example.minkr.jeonju_all.util.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-07.
 */

public class FacilityActivity extends AppCompatActivity implements PoliceView, MedicineView, ParkView, ParkingVIew{

    @BindView(R.id.rl_health)
    RelativeLayout rl_health;

    @BindView(R.id.rl_hospital)
    RelativeLayout rl_hospital;

    @BindView(R.id.rl_medichine)
    RelativeLayout rl_medichine;

    @BindView(R.id.rl_park)
    RelativeLayout rl_park;

    @BindView(R.id.rl_parking)
    RelativeLayout rl_parking;

    @BindView(R.id.rl_police)
    RelativeLayout rl_police;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    PolicePresenter policePresenter;
    List<FacilityListData> police_datas = new ArrayList<>();

    MedicinePresenter medicinePresenter;
    List<HospitalListData> medicine_datas = new ArrayList<>();

    List<ParkListData> park_datas = new ArrayList<>();
    ParkPresenter parkPresenter;

    ParkingPresenter parkingPresenter;
    List<ParkingListData> parking_datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);
        ButterKnife.bind(this);

        policePresenter = new PolicePresenter();
        policePresenter.attachView(this);
        policePresenter.getPoliceList();

        medicinePresenter = new MedicinePresenter();
        medicinePresenter.attachView(this);
        medicinePresenter.getMedicineList();

        parkPresenter = new ParkPresenter();
        parkPresenter.attachView(this);
        parkPresenter.getParkList();

        parkingPresenter = new ParkingPresenter();
        parkingPresenter.attachView(this);
        parkingPresenter.getParkingList();

        setListener();
    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());

        rl_police.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, PoliceMapActivity.class);
            intent.putExtra("data", (Serializable) police_datas);
            startActivity(intent);
        });

        rl_hospital.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, HospitalActivity.class);
            startActivity(intent);
        });

        rl_medichine.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, MedicineMapActivity.class);
            intent.putExtra("data", (Serializable) medicine_datas);
            startActivity(intent);
        });

        rl_park.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, ParkMapActivity.class);
            intent.putExtra("data", (Serializable) park_datas);
            startActivity(intent);
        });

        rl_parking.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, ParkingMapActivity.class);
            intent.putExtra("data", (Serializable) parking_datas);
            startActivity(intent);
        });

        rl_health.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, HealthActivity.class);
            startActivity(intent);
        });
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
        policePresenter.detachView();
        medicinePresenter.detachView();
        parkPresenter.detachView();
        parkingPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void getPoliceList(List<FacilityListData> facilityListData) {
        police_datas.addAll(facilityListData);
        Logger.log("#22 data -> "+police_datas);
    }

    @Override
    public void getMedicineList(List<HospitalListData> hospitalListData) {
        medicine_datas.addAll(hospitalListData);
        Logger.log("#23 data -> "+medicine_datas);
    }

    @Override
    public void getParkList(List<ParkListData> parkListData) {
        park_datas.addAll(parkListData);
        Logger.log("#24 data -> "+park_datas);
    }

    @Override
    public void getParkingList(List<ParkingListData> parkingListData) {
        parking_datas.addAll(parkingListData);
        Logger.log("#25 data -> "+parking_datas);
    }
}
