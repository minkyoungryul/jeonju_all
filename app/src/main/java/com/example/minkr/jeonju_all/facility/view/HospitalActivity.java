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
        setContentView(R.layout.activity_police);
        ButterKnife.bind(this);

        presenter = new HospitalPresenter();
        presenter.attachView(this);
        presenter.getHospitalList();
        presenter.getClinicList();
        presenter.getOriginalList();
        presenter.getPostpartumList();
        presenter.getDentistList();

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
