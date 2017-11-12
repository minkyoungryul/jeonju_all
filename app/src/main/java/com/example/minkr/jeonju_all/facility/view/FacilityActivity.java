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
import com.example.minkr.jeonju_all.parking.view.ParkingActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-07.
 */

public class FacilityActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);
        ButterKnife.bind(this);

        setListener();
    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());

        rl_police.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, PoliceActivity.class);
            startActivity(intent);
        });

        rl_hospital.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, HospitalActivity.class);
            startActivity(intent);
        });

        rl_medichine.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, MedicineActivity.class);
            startActivity(intent);
        });

        rl_park.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, ParkActivity.class);
            startActivity(intent);
        });

        rl_parking.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, ParkingActivity.class);
            startActivity(intent);
        });

        rl_health.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, HealthActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
