package com.example.minkr.jeonju_all.facility.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.facility.data.FacilityListData;
import com.example.minkr.jeonju_all.facility.data.HospitalListData;
import com.example.minkr.jeonju_all.facility.data.ParkListData;
import com.example.minkr.jeonju_all.facility.presenter.HospitalPresenter;
import com.example.minkr.jeonju_all.facility.presenter.MedicinePresenter;
import com.example.minkr.jeonju_all.facility.presenter.ParkPresenter;
import com.example.minkr.jeonju_all.facility.presenter.PolicePresenter;
import com.example.minkr.jeonju_all.parking.data.ParkingListData;
import com.example.minkr.jeonju_all.parking.presenter.ParkingPresenter;
import com.example.minkr.jeonju_all.parking.view.ParkingActivity;
import com.tsengvn.typekit.TypekitContextWrapper;
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

public class FacilityActivity extends AppCompatActivity implements PoliceView, MedicineView, ParkView, ParkingVIew, HospitalView{

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

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @BindView(R.id.ll_all)
    LinearLayout ll_all;

    PolicePresenter policePresenter;
    List<FacilityListData> police_datas = new ArrayList<>();

    MedicinePresenter medicinePresenter;
    List<HospitalListData> medicine_datas = new ArrayList<>();

    List<ParkListData> park_datas = new ArrayList<>();
    ParkPresenter parkPresenter;

    ParkingPresenter parkingPresenter;
    List<ParkingListData> parking_datas = new ArrayList<>();

    HospitalPresenter hospitalPresenter;

    //    병원
    List<HospitalListData> hospital_all_datas = new ArrayList<>();
    List<HospitalListData> hospital_total_datas = new ArrayList<>(); // 종합병원
    List<HospitalListData> hospital_nomarl_datas = new ArrayList<>(); // 일반병원
    List<HospitalListData> hospital_grand_datas = new ArrayList<>(); //요양병원
    List<HospitalListData> hospital_child_datas = new ArrayList<>(); //아동병원
    List<HospitalListData> hospital_dentist_datas = new ArrayList<>(); //치과병원
    List<HospitalListData> hospital_korea_datas = new ArrayList<>(); //한방병원

    //    클리닉
    List<HospitalListData> clinic_all_datas = new ArrayList<>();
    List<HospitalListData> clinic_internal_datas = new ArrayList<>(); //내과
    List<HospitalListData> clinic_child_datas = new ArrayList<>(); //소아청소년과
    List<HospitalListData> clinic_ear_datas = new ArrayList<>();    //이비인후과
    List<HospitalListData> clinic_family_datas = new ArrayList<>(); //가정의학과
    List<HospitalListData> clinic_normal_datas = new ArrayList<>(); //일반의원
    List<HospitalListData> clinic_baby_datas = new ArrayList<>(); //산부인과
    List<HospitalListData> clinic_skin_datas = new ArrayList<>(); //피부과
    List<HospitalListData> clinic_born_datas = new ArrayList<>(); //정형외과
    List<HospitalListData> clinic_surgery_datas = new ArrayList<>(); //외과
    List<HospitalListData> clinic_eye_datas = new ArrayList<>(); //안과
    List<HospitalListData> clinic_rehabit_datas = new ArrayList<>(); //재활의학과
    List<HospitalListData> clinic_psy_datas = new ArrayList<>(); //정신의학과
    List<HospitalListData> clinic_dentist_datas = new ArrayList<>(); //치과
    List<HospitalListData> clinic_neuro_datas = new ArrayList<>(); //신경외과

    //    한의원
    List<HospitalListData> original_datas = new ArrayList<>();

    //    산후조리원
    List<HospitalListData> postpartum_datas = new ArrayList<>();

    //    치과의원
    List<HospitalListData> dentist_datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);
        ButterKnife.bind(this);

        progress_bar.setVisibility(View.VISIBLE);
        ll_all.setVisibility(View.GONE);

        policePresenter = new PolicePresenter();
        policePresenter.attachView(this);

        medicinePresenter = new MedicinePresenter();
        medicinePresenter.attachView(this);

        parkPresenter = new ParkPresenter();
        parkPresenter.attachView(this);

        parkingPresenter = new ParkingPresenter();
        parkingPresenter.attachView(this);

        hospitalPresenter = new HospitalPresenter();
        hospitalPresenter.attachView(this);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                policePresenter.getPoliceList();
                medicinePresenter.getMedicineList();
                parkPresenter.getParkList();
                parkingPresenter.getParkingList();
                hospitalPresenter.getHospitalList();
                hospitalPresenter.getClinicList();
                hospitalPresenter.getOriginalList();
                hospitalPresenter.getPostpartumList();
                hospitalPresenter.getDentistList();
            }
        },500);


        setListener();
    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());

        rl_police.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, PoliceMapActivity.class);
            intent.putExtra("data", (Serializable) police_datas);
            intent.putExtra("type","경찰서");
            startActivity(intent);
        });

        rl_hospital.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, HospitalActivity.class);
            intent.putExtra("type","병원");
            startActivity(intent);
        });

        rl_medichine.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, MedicineMapActivity.class);
            intent.putExtra("data", (Serializable) medicine_datas);
            intent.putExtra("type","약국");
            startActivity(intent);
        });

        rl_park.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, ParkMapActivity.class);
            intent.putExtra("data", (Serializable) park_datas);
            intent.putExtra("type","공원");
            startActivity(intent);
        });

        rl_parking.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, ParkingMapActivity.class);
            intent.putExtra("data", (Serializable) parking_datas);
            intent.putExtra("type","주차장");
            startActivity(intent);
        });

        rl_health.setOnClickListener(v->{
            Intent intent = new Intent(FacilityActivity.this, HealthActivity.class);
            intent.putExtra("type","체육시설");
            startActivity(intent);
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
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
        hospitalPresenter.detachView();
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

    @Override
    public void getHospitalList(List<HospitalListData> hospitalListData) {
        hospital_all_datas.addAll(hospitalListData);
        for(int i=0; i<hospitalListData.size(); i++){
            if(hospitalListData.get(i).getMediCdmStr().equals("종합병원")){
                hospital_total_datas.add(hospitalListData.get(i));
            } else if(hospitalListData.get(i).getMediCdmStr().equals("일반병원")){
                hospital_nomarl_datas.add(hospitalListData.get(i));
            } else if(hospitalListData.get(i).getMediCdmStr().equals("요양병원")){
                hospital_grand_datas.add(hospitalListData.get(i));
            } else if(hospitalListData.get(i).getMediCdmStr().equals("아동병원")){
                hospital_child_datas.add(hospitalListData.get(i));
            } else if(hospitalListData.get(i).getMediCdmStr().equals("치과병원")){
                hospital_dentist_datas.add(hospitalListData.get(i));
            } else{
                hospital_korea_datas.add(hospitalListData.get(i));
            }
        }
    }


    @Override
    public void getClinicList(List<HospitalListData> hospitalListData) {
        clinic_all_datas.addAll(hospitalListData);
        for(int i=0; i<hospitalListData.size(); i++){
            if(hospitalListData.get(i).getMediCdmStr().equals("내과")){
                clinic_internal_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("소아청소년과")){
                clinic_child_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("이비인후과")){
                clinic_ear_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("가정의학과")){
                clinic_family_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("일반의원")){
                clinic_normal_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("산부인과")){
                clinic_baby_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("피부과")){
                clinic_skin_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("정형외과")){
                clinic_born_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("외과")){
                clinic_surgery_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("안과")){
                clinic_eye_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("재활의학과")){
                clinic_neuro_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("정신건강의학과")){
                clinic_psy_datas.addAll(hospitalListData);
            }else if(hospitalListData.get(i).getMediCdmStr().equals("치과")){
                clinic_dentist_datas.addAll(hospitalListData);
            }else{
                clinic_neuro_datas.addAll(hospitalListData);
            }
        }

        ll_all.setVisibility(View.VISIBLE);
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void getOriginalList(List<HospitalListData> hospitalListData) {
        original_datas.addAll(hospitalListData);
    }

    @Override
    public void getPostpartumList(List<HospitalListData> hospitalListData) {
        postpartum_datas.addAll(hospitalListData);
    }

    @Override
    public void getDentistList(List<HospitalListData> hospitalListData) {
        dentist_datas.addAll(hospitalListData);
    }
}
