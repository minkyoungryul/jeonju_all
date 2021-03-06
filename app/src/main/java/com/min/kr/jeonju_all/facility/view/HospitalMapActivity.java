package com.min.kr.jeonju_all.facility.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.facility.data.HospitalListData;
import com.min.kr.jeonju_all.facility.presenter.HospitalPresenter;
import com.min.kr.jeonju_all.house.view.map.NMapCalloutCustomOverlayView;
import com.min.kr.jeonju_all.kindFood.view.map.NMapPOIflagType;
import com.min.kr.jeonju_all.kindFood.view.map.NMapViewerResourceProvider;
import com.min.kr.jeonju_all.util.Logger;
import com.min.kr.jeonju_all.util.Variable;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.NMapView.OnMapViewTouchEventListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

;

/**
 * Created by minkr on 2017-10-26.
 */

public class HospitalMapActivity extends NMapActivity implements OnMapStateChangeListener,OnMapViewTouchEventListener,
        NMapOverlayManager.OnCalloutOverlayViewListener,NMapPOIdataOverlay.OnStateChangeListener, HospitalView{

    private final String API_KEY = "5lvyL1UwyDdfnK1Xxig6";
    NMapController mMapController = null;

    NMapViewerResourceProvider nMapViewerResourceProvider = null;
    NMapOverlayManager nMapOverlayManager;
    NMapLocationManager nMapLocationManager;
    NMapMyLocationOverlay nMapMyLocationOverlay;
    NMapCompassManager nMapCompassManager;
    NMapPOIdata poiData;

    @BindView(R.id.mapView)
    NMapView mMapView = null;

    @BindView(R.id.txtLocation)
    TextView txtLocation;

    @BindView(R.id.imgbt1)
    ImageButton imgbtLocation;

    @BindView(R.id.progressBarMap)
    ProgressBar progressBar;

    @BindView(R.id.layout_progressbar)
    LinearLayout layoutProgressbar;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.spinner1)
    Spinner spinner1;

    @BindView(R.id.spinner2)
    Spinner spinner2;

    //List<FacilityListData> datas = new ArrayList<>();

    int locationType = 0;
    double myLocationX = 0.0;
    double myLocationY = 0.0;

    int listPosition = 0;
    int listPosition2 = 0;

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list3 = new ArrayList<>();
    ArrayList<String> list4 = new ArrayList<>();

    ArrayAdapter spinnerAdapter;
    ArrayAdapter hospitalAdapter;


    List<HospitalListData> datas,hospital_all_datas,hospital_total_datas,hospital_nomarl_datas,hospital_grand_datas,hospital_child_datas,
            hospital_dentist_datas,hospital_korea_datas,original_datas,postpartum_datas,dentist_datas;

    HospitalPresenter presenter;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        datas = hospital_all_datas = (List<HospitalListData>) intent.getSerializableExtra("hospital_all_datas");
        hospital_total_datas = (List<HospitalListData>) intent.getSerializableExtra("hospital_total_datas");
        hospital_nomarl_datas = (List<HospitalListData>) intent.getSerializableExtra("hospital_nomarl_datas");
        hospital_grand_datas = (List<HospitalListData>) intent.getSerializableExtra("hospital_grand_datas");
        hospital_child_datas = (List<HospitalListData>) intent.getSerializableExtra("hospital_child_datas");
        hospital_dentist_datas = (List<HospitalListData>) intent.getSerializableExtra("hospital_dentist_datas");
        hospital_korea_datas = (List<HospitalListData>) intent.getSerializableExtra("hospital_korea_datas");

        original_datas = (List<HospitalListData>) intent.getSerializableExtra("original_datas");
        postpartum_datas = (List<HospitalListData>) intent.getSerializableExtra("postpartum_datas");
        dentist_datas = (List<HospitalListData>) intent.getSerializableExtra("dentist_datas");

        presenter = new HospitalPresenter();
        presenter.attachView(this);

        if(Variable.isOnline(Variable.CONNECTION_CONFIRM_CLIENT_URL)){
            presenter.getClinicList();
        }else{
            Toast.makeText(this, "인터넷 연결을 확인해주세요.",Toast.LENGTH_SHORT).show();
            finish();
        }


        setSpinnerItem();
        init();
        setListener();
    }

    public void init(){
        doLocationThing();
        tv_title.setText("병원");
    }

    public void setSpinnerItem(){

        list1.add("병원");
        list1.add("클리닉");
        list1.add("한의원");
        list1.add("산후조리원");
        list1.add("치과의원");

        list2.add("전체");
        list2.add("종합병원");
        list2.add("일반병원");
        list2.add("요양병원");
        list2.add("아동병원");
        list2.add("치과병원");
        list2.add("한방병원");

        list3.add("전체");
        list3.add("내과");
        list3.add("소아청소년과");
        list3.add("이비인후과");
        list3.add("가정의학과");
        list3.add("일반의원");
        list3.add("산부인과");
        list3.add("피부과");
        list3.add("정형외과");
        list3.add("외과");
        list3.add("안과");
        list3.add("재활의학과");
        list3.add("정신의학과");
        list3.add("치과");
        list3.add("신경외과");

        list4.add("-");

        spinnerAdapter = new ArrayAdapter(this,R.layout.spinner_center,list1);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_center);
        spinner1.setAdapter(spinnerAdapter);

        hospitalAdapter = new ArrayAdapter(this,R.layout.spinner_center,list4);
        hospitalAdapter.setDropDownViewResource(R.layout.spinner_center);
        spinner2.setAdapter(hospitalAdapter);
    }

    public void setListener(){
        imgbtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (locationType == 0){
                        stopMyLocation();
                        startMyLocation();
                    }else {
                        imgbtLocation.setImageResource(R.drawable.mylocation);
                        imgbtLocation.setBackgroundResource(R.drawable.mylocation);
                        txtLocation.setText("현재위치");
                        stopMyLocation();
                        locationType = 0;
                        mMapController.setMapCenter(new NGeoPoint(127.1480000, 35.8241930), 10);
                    }
                }

        });

        layoutProgressbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        ib_back.setOnClickListener(v->finish());

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    hospitalAdapter = new ArrayAdapter(HospitalMapActivity.this,R.layout.spinner_center,list2);
                    listPosition = 0;
                }else if (position == 1){
                    hospitalAdapter = new ArrayAdapter(HospitalMapActivity.this,R.layout.spinner_center,list3);
                    listPosition = 1;
                }else if (position == 2){
                    hospitalAdapter = new ArrayAdapter(HospitalMapActivity.this,R.layout.spinner_center,list4);
                    listPosition = 2;
                }else if (position == 3){
                    hospitalAdapter = new ArrayAdapter(HospitalMapActivity.this,R.layout.spinner_center,list4);
                    listPosition = 3;
                }else if (position == 4){
                    hospitalAdapter = new ArrayAdapter(HospitalMapActivity.this,R.layout.spinner_center,list4);
                    listPosition = 4;
                }else{
                    hospitalAdapter = new ArrayAdapter(HospitalMapActivity.this,R.layout.spinner_center,list4);
                    listPosition = 5;
                }

                hospitalAdapter.setDropDownViewResource(R.layout.spinner_center);
                spinner2.setAdapter(hospitalAdapter);

                //Toast.makeText(HospitalMapActivity.this,"position -> "+position,Toast.LENGTH_LONG).show();
                Logger.log("#90 item "+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                poiData.removeAllPOIdata();
                nMapOverlayManager.clearOverlays();

                if (listPosition == 0){//병원
                    if (position == 0){//전체
                        datas = hospital_all_datas;
                        listPosition2 = 0;
                    }else if (position == 1){//종합병원
                        datas = hospital_total_datas;
                        listPosition2 = 1;
                    }else if (position == 2){//일반병원
                        datas = hospital_nomarl_datas;
                        listPosition2 = 2;
                    }else if (position == 3){//요양병원
                        datas = hospital_grand_datas;
                        listPosition2 = 3;
                    }else if (position == 4){//아동병원
                        datas = hospital_child_datas;
                        listPosition2 = 4;
                    }else if (position == 5){//치과병원
                        datas = hospital_dentist_datas;
                        listPosition2 = 5;
                    }else{//한방병원
                        datas = hospital_korea_datas;
                        listPosition2 = 6;
                    }
                }else if (listPosition == 1){//클리닉
                    if (position == 0){//전체
                        datas = clinic_all_datas;
                        listPosition2 = 0;
                    }else if (position == 1){//내과
                        datas = clinic_internal_datas;
                        listPosition2 = 1;
                    }else if (position == 2){//소아청소년과
                        datas = clinic_child_datas;
                        listPosition2 = 2;
                    }else if (position == 3){//이비인후과
                        datas = clinic_ear_datas;
                        listPosition2 = 3;
                    }else if (position == 4){//가정의학과
                        datas = clinic_family_datas;
                        listPosition2 = 4;
                    }else if (position == 5){//일반의원
                        datas = clinic_normal_datas;
                        listPosition2 = 5;
                    }else if (position == 6){//산부인과
                        datas = clinic_baby_datas;
                        listPosition2 = 6;
                    }else if (position == 7){//피부과
                        datas = clinic_skin_datas;
                        listPosition2 = 7;
                    }else if (position == 8){//정형외과
                        datas = clinic_born_datas;
                        listPosition2 = 8;
                    }else if (position == 9){//외과
                        datas = clinic_surgery_datas;
                        listPosition2 = 9;
                    }else if (position == 10){//안과
                        datas = clinic_eye_datas;
                        listPosition2 = 10;
                    }else if (position == 11){//재활의학
                        datas = clinic_rehabit_datas;
                        listPosition2 = 11;
                    }else if (position == 12){//정신건강의학과
                        datas = clinic_psy_datas;
                        listPosition2 = 12;
                    }else if (position == 13){//치과
                        datas = clinic_dentist_datas;
                        listPosition2 = 13;
                    }else{
                        datas = clinic_neuro_datas;
                        listPosition2 = 14;
                    }
                }else if (listPosition == 2){//한의원
                        datas = original_datas;
                }else if (listPosition == 3){//산후조리원
                        datas = postpartum_datas;
                }else if (listPosition == 4){//치과의원
                        datas = dentist_datas;
                }else{

                }
                //Toast.makeText(HospitalMapActivity.this,"position -> "+position,Toast.LENGTH_LONG).show();

                doLocationThing();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void doLocationThing() {
        //Toast.makeText(this, "위치정보 활용에 동의하셨습니다.", Toast.LENGTH_SHORT).show();

        Logger.log("#22 dolocation start");

        mMapController = mMapView.getMapController();

        mMapView.setApiKey(API_KEY); // 클라이언트 아이디 값 설정

        mMapView.setScalingFactor(2.0f);

        mMapView.setClickable(true);

        mMapView.setBuiltInZoomControls(true, null);

        mMapView.setOnMapStateChangeListener(this);


        nMapViewerResourceProvider = new NMapViewerResourceProvider(this);

        nMapOverlayManager = new NMapOverlayManager(this,mMapView,nMapViewerResourceProvider);


        int markerId = NMapPOIflagType.PIN;
        poiData = new NMapPOIdata(datas.size(),nMapViewerResourceProvider);
        poiData.beginPOIdata(datas.size());

        for (int i = 0; i<datas.size();i++){
            if (datas.get(i).getPosX().toString().equals(" ")){

            }else{
                double x = Double.parseDouble(datas.get(i).getPosX());
                double y = Double.parseDouble(datas.get(i).getPosY());
                poiData.addPOIitem(x,y,datas.get(i).getMediName(),markerId,0);
            }
        }

        poiData.endPOIdata();
        NMapPOIdataOverlay poiDataOverlay = nMapOverlayManager.createPOIdataOverlay(poiData,null);

        poiDataOverlay.showAllPOIdata(0);
        poiDataOverlay.setOnStateChangeListener((NMapPOIdataOverlay.OnStateChangeListener)this);

        nMapOverlayManager.setOnCalloutOverlayViewListener((NMapOverlayManager.OnCalloutOverlayViewListener)this);

        nMapLocationManager = new NMapLocationManager(this);
        nMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);

        // compass manager
        nMapCompassManager = new NMapCompassManager(this);

        // create my location overlay
        nMapMyLocationOverlay = nMapOverlayManager.createMyLocationOverlay(nMapLocationManager, nMapCompassManager);

        if (listPosition == 0 && listPosition2 == 4){
            mMapController.setMapCenter(new NGeoPoint(127.1114600, 35.8155320),10);
        }else if (listPosition == 0 && listPosition2 == 5){
            mMapController.setMapCenter(new NGeoPoint(127.1364460, 35.8373860),10);
        }else if (listPosition == 1 && listPosition2 == 13){
            mMapController.setMapCenter(new NGeoPoint(127.1087270, 35.8203600), 10);
        }else{
            mMapController.setMapCenter(new NGeoPoint(127.1480000, 35.8241930), 10);
        }

        Logger.log("#22 dolocation end");

    }


    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null){
            mMapController.setMapCenter(new NGeoPoint(127.1480000, 35.8241930),10);
        }else{
        }
    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {}

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {}

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {}

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {}

    @Override
    public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {}

    @Override
    public void onLongPressCanceled(NMapView nMapView) {}

    @Override
    public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {}

    @Override
    public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {}

    @Override
    public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {}

    @Override
    public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {}

    @Override
    public View onCreateCalloutOverlayView(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {

        String clickStore = nMapOverlayItem.getTitle();

        String ceoName = "";

        String type = "";
        String address = "";
        String tel = "";
        String name = "";
        Double x = 0.0;
        Double y = 0.0;


        for (int i=0;i<datas.size();i++){
            if (clickStore.equals(datas.get(i).getMediName())){
                name = datas.get(i).getMediName();
                address = datas.get(i).getAddress();
                tel = datas.get(i).getMediTel()+"(063)";
                type = datas.get(i).getMediConDate()+"("+datas.get(i).getMediStime()+" ~ "+datas.get(i).getMediEtime()+")";
                x = Double.parseDouble(datas.get(i).getPosX());
                y = Double.parseDouble(datas.get(i).getPosY());
            }else{

            }
        }


        return new NMapCalloutCustomOverlayView(HospitalMapActivity.this, nMapOverlay, nMapOverlayItem, rect, name, address, tel, type, ceoName, x, y, 60);
    }

    @Override
    public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {}

    //마커 풍선말 클릭시 넘어가는 메소드
    @Override
    public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {}


    //현재위치 찾기
    public void startMyLocation() {

        if (nMapMyLocationOverlay != null) {
            if (!nMapOverlayManager.hasOverlay(nMapMyLocationOverlay)) {
                nMapOverlayManager.addOverlay(nMapMyLocationOverlay);
            }

            if (nMapLocationManager.isMyLocationEnabled()) {

                if (!mMapView.isAutoRotateEnabled()) {
                    nMapMyLocationOverlay.setCompassHeadingVisible(true);

                    nMapCompassManager.enableCompass();

                    mMapView.setAutoRotateEnabled(true, false);
                } else {
                    stopMyLocation();
                }

                mMapView.postInvalidate();
            } else {
                boolean isMyLocationEnabled = nMapLocationManager.enableMyLocation(true);
                if (!isMyLocationEnabled) {

                    setAlertDialogShow();

                    return;
                }else{
                    layoutProgressbar.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    imgbtLocation.setImageResource(R.drawable.jeonjulocation);
                    imgbtLocation.setBackgroundResource(R.drawable.jeonjulocation);
                    txtLocation.setText("전주위치");
                    Toast.makeText(HospitalMapActivity.this,"현재위치를 찾고 있습니다...",Toast.LENGTH_LONG).show();
                    locationType = 1;
                }
            }
        }
    }

    public void stopMyLocation() {
        if (nMapMyLocationOverlay != null) {
            nMapLocationManager.disableMyLocation();

            if (mMapView.isAutoRotateEnabled()) {
                nMapMyLocationOverlay.setCompassHeadingVisible(false);

                nMapCompassManager.disableCompass();
                mMapView.setAutoRotateEnabled(false, false);
            }
        }
    }


    public void setRoad(String name){

        boolean isMyLocationEnabled = nMapLocationManager.enableMyLocation(true);
        if (!isMyLocationEnabled) {
            setAlertDialogShow();

            return;
        }else{

            if (myLocationX == 0 || myLocationY == 0){
                setRoadLocation();
            }else{
                Logger.log("#50 mylocation3 -> "+ myLocationX);
                String uri ="http://maps.google.com/maps?saddr="+myLocationX+","+myLocationY+"&daddr="+name+"&hl=ko";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_LAUNCHER );
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        }

    }

    public void getStreetView(double x, double y){
        Logger.log("#90 kind streetView -> x : "+x+" y : "+y);
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+x+","+y);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void setAlertDialogShow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("현재위치 활용 동의");
        builder.setMessage("현재위치를 활용해서 길찾기등의 서비스를 위하여 현재위치를 On 시키겠습니까?\n(다른목적으로는 사용하지 않습니다.)");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(goToSettings);
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"위치를 On 하셔야 현재위치 서비스를 사용할 수 있습니다.",Toast.LENGTH_LONG).show();
                        //finish();
                    }
                });
        builder.show();
    }

    public void setRoadLocation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("길안내 사용방법");
        builder.setMessage("길안내기능은 현재위치를 받아와야 합니다.\n\n1. 화면우측상단 현재위치 클릭\n2. 다시 화면우측상단 가게위치 클릭\n3. 해당가게의 길안내 다시 클릭");
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }




    /* MyLocation Listener */
    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

        @Override
        public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {


            if (mMapController != null) {

                Logger.log("#50 mylocation -> "+myLocation.getLatitude());
                myLocationX = myLocation.getLatitude();
                myLocationY = myLocation.getLongitude();
                Logger.log("#50 mylocation2 ->" +myLocation.getLatitude());

                Logger.log("#50 else");
                if(locationType == 1) {
                    mMapController.setMapCenter(new NGeoPoint(myLocation.getLongitude(), myLocation.getLatitude()), 10);
                    mMapController.animateTo(myLocation);
                    progressBar.setVisibility(View.GONE);
                    layoutProgressbar.setVisibility(View.INVISIBLE);
                }

                Logger.log("#50 mylocation changerd -> "+myLocationX);
            }

            //findPlacemarkAtLocation(myLocation.getLongitude(), myLocation.getLatitude());

            return true;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager locationManager) {
            //stopMyLocation();
            //Toast.makeText(KindFoodMapActivity.this, "현재지역은 사용할 수 없는 지역입니다.", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {
            //Toast.makeText(KindFoodMapActivity.this, "현재지역은 사용할 수 없는 지역입니다.", Toast.LENGTH_LONG).show();
            //stopMyLocation();
        }

    };

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
    public void getHospitalList(List<HospitalListData> hospitalListData) {
    }

    @Override
    public void getClinicList(List<HospitalListData> hospitalListData) {
        clinic_all_datas.addAll(hospitalListData);
        for(int i=0; i<hospitalListData.size(); i++){
            if(hospitalListData.get(i).getMediCdmStr().equals("내과")){
                clinic_internal_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("소아청소년과")){
                clinic_child_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("이비인후과")){
                clinic_ear_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("가정의학과")){
                clinic_family_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("일반의원")){
                clinic_normal_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("산부인과")){
                clinic_baby_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("피부과")){
                clinic_skin_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("정형외과")){
                clinic_born_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("외과")){
                clinic_surgery_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("안과")){
                clinic_eye_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("재활의학과")){
                clinic_rehabit_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("정신건강의학과")){
                clinic_psy_datas.add(hospitalListData.get(i));
            }else if(hospitalListData.get(i).getMediCdmStr().equals("치과")){
                clinic_dentist_datas.add(hospitalListData.get(i));
            }else{
                clinic_neuro_datas.add(hospitalListData.get(i));
            }
        }


        Logger.log("#106 clinic_internal_datas -> "+clinic_internal_datas);
        Logger.log("#106 clinic_child_datas -> "+clinic_child_datas);


    }

    @Override
    public void getOriginalList(List<HospitalListData> hospitalListData) {
    }

    @Override
    public void getDentistList(List<HospitalListData> hospitalListData) {
    }

    @Override
    public void getPostpartumList(List<HospitalListData> hospitalListData) {
    }
}
