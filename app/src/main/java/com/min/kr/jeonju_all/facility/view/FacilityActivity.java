package com.min.kr.jeonju_all.facility.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.facility.data.FacilityListData;
import com.min.kr.jeonju_all.facility.data.HealthListData;
import com.min.kr.jeonju_all.facility.data.HospitalListData;
import com.min.kr.jeonju_all.facility.data.ParkListData;
import com.min.kr.jeonju_all.facility.presenter.HealthPresenter;
import com.min.kr.jeonju_all.facility.presenter.HospitalPresenter;
import com.min.kr.jeonju_all.facility.presenter.MedicinePresenter;
import com.min.kr.jeonju_all.facility.presenter.ParkPresenter;
import com.min.kr.jeonju_all.facility.presenter.PolicePresenter;
import com.min.kr.jeonju_all.parking.data.ParkingListData;
import com.min.kr.jeonju_all.parking.presenter.ParkingPresenter;
import com.min.kr.jeonju_all.util.Variable;
import com.tsengvn.typekit.TypekitContextWrapper;
import com.min.kr.jeonju_all.parking.view.ParkingMapActivity;
import com.min.kr.jeonju_all.parking.view.ParkingVIew;
import com.min.kr.jeonju_all.util.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-07.
 */

public class FacilityActivity extends AppCompatActivity implements PoliceView, MedicineView, ParkView, ParkingVIew, HealthView, HospitalView{

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

    HealthPresenter healthPresenter;
    List<HealthListData> health_datas = new ArrayList<>();

    HospitalPresenter hospitalPresenter;

    //    병원
    List<HospitalListData> hospital_all_datas = new ArrayList<>();
    List<HospitalListData> hospital_total_datas = new ArrayList<>(); // 종합병원
    List<HospitalListData> hospital_nomarl_datas = new ArrayList<>(); // 일반병원
    List<HospitalListData> hospital_grand_datas = new ArrayList<>(); //요양병원
    List<HospitalListData> hospital_child_datas = new ArrayList<>(); //아동병원
    List<HospitalListData> hospital_dentist_datas = new ArrayList<>(); //치과병원
    List<HospitalListData> hospital_korea_datas = new ArrayList<>(); //한방병원



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

        healthPresenter = new HealthPresenter();
        healthPresenter.attachView(this);

        if(Variable.isOnline(Variable.CONNECTION_CONFIRM_CLIENT_URL)){
            policePresenter.getPoliceList();
        }else{
            Toast.makeText(this, "인터넷 연결을 확인해주세요.",Toast.LENGTH_SHORT).show();
            finish();
        }

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
            Intent intent = new Intent(FacilityActivity.this, HospitalMapActivity.class);
            Logger.log("#110 datas -> "+hospital_all_datas);
            intent.putExtra("hospital_all_datas", (Serializable) hospital_all_datas);
            intent.putExtra("hospital_total_datas", (Serializable) hospital_total_datas);
            intent.putExtra("hospital_nomarl_datas", (Serializable) hospital_nomarl_datas);
            intent.putExtra("hospital_grand_datas", (Serializable) hospital_grand_datas);
            intent.putExtra("hospital_child_datas", (Serializable) hospital_child_datas);
            intent.putExtra("hospital_dentist_datas", (Serializable) hospital_dentist_datas);
            intent.putExtra("hospital_korea_datas", (Serializable) hospital_korea_datas);

            intent.putExtra("original_datas", (Serializable) original_datas);//한의원
            intent.putExtra("postpartum_datas", (Serializable) postpartum_datas);//산후조리원
            intent.putExtra("dentist_datas", (Serializable) dentist_datas);//치과의원

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
            Intent intent = new Intent(FacilityActivity.this, HealthMapActivity.class);
            intent.putExtra("data", (Serializable) health_datas);
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
        healthPresenter.detachView();
        hospitalPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void getPoliceList(List<FacilityListData> facilityListData) {
        police_datas.addAll(facilityListData);
        Logger.log("#22 data -> "+police_datas);

        hospitalPresenter.getHospitalList();
    }

    @Override
    public void getMedicineList(List<HospitalListData> hospitalListData) {
        medicine_datas.addAll(hospitalListData);
        Logger.log("#23 data -> "+medicine_datas);

        parkPresenter.getParkList();
    }

    @Override
    public void getParkList(List<ParkListData> parkListData) {
        park_datas.addAll(parkListData);
        Logger.log("#24 data -> "+park_datas);

        parkingPresenter.getParkingList();
    }

    @Override
    public void getParkingList(List<ParkingListData> parkingListData) {
        parking_datas.addAll(parkingListData);
        Logger.log("#25 data -> "+parking_datas);

        healthPresenter.getHealthList();
    }

    @Override
    public void getHealthList(List<HealthListData> healthListData) {
        health_datas.addAll(healthListData);
        Logger.log("#26 data -> "+health_datas);

        ll_all.setVisibility(View.VISIBLE);
        progress_bar.setVisibility(View.GONE);
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
//        hospitalPresenter.getClinicList();
        hospitalPresenter.getOriginalList();
    }


    @Override
    public void getClinicList(List<HospitalListData> hospitalListData) {
    }

    @Override
    public void getOriginalList(List<HospitalListData> hospitalListData) {
        original_datas.addAll(hospitalListData);

        hospitalPresenter.getPostpartumList();
    }

    @Override
    public void getPostpartumList(List<HospitalListData> hospitalListData) {
        postpartum_datas.addAll(hospitalListData);

        hospitalPresenter.getDentistList();
    }

    @Override
    public void getDentistList(List<HospitalListData> hospitalListData) {
        dentist_datas.addAll(hospitalListData);

        medicinePresenter.getMedicineList();
    }
}
