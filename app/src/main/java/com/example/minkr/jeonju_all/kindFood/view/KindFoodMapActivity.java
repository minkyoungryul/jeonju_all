package com.example.minkr.jeonju_all.kindFood.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;
import com.example.minkr.jeonju_all.kindFood.presenter.KindFoodPresenter;
import com.example.minkr.jeonju_all.util.Logger;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapViewTouchEventListener;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-10-26.
 */

public class KindFoodMapActivity extends NMapActivity implements OnMapStateChangeListener,OnMapViewTouchEventListener,
        NMapOverlayManager.OnCalloutOverlayViewListener,NMapPOIdataOverlay.OnStateChangeListener{

    private final String API_KEY = "5lvyL1UwyDdfnK1Xxig6";
    NMapController mMapController = null;

    //List<KindFoodListData> datas;

    NMapViewerResourceProvider nMapViewerResourceProvider = null;
    NMapOverlayManager nMapOverlayManager;
    NMapLocationManager nMapLocationManager;
    NMapMyLocationOverlay nMapMyLocationOverlay;
    NMapCompassManager nMapCompassManager;

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

    int locationType = 0;
    int initLocation = 0;
    double myLocationX = 0.0;
    double myLocationY = 0.0;

    //List<KindFoodListData> datas = new ArrayList<>();


    //위치정보 허가
    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int LOCATION_REQUEST=3;

    List<KindFoodListData> datas;

    KindFoodListData data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_food_map);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        datas = (List<KindFoodListData>) intent.getSerializableExtra("data");

        Logger.log("#21 datas -> "+datas);

        init();
        setListener();

        startMyLocation();

    }

    public void init(){
        if (canAccessLocation()) {
            doLocationThing();
            //Logger.log("#16 datas-> "+datas);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
            }
        }
    }

    public void setListener(){
        imgbtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationType == 0){
                    layoutProgressbar.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    imgbtLocation.setImageResource(R.drawable.jeonjulocation);
                    imgbtLocation.setBackgroundResource(R.drawable.jeonjulocation);
                    txtLocation.setText("전주위치");
                    stopMyLocation();
                    startMyLocation();
                    locationType = 1;
                }else {
                    imgbtLocation.setImageResource(R.drawable.mylocation);
                    imgbtLocation.setBackgroundResource(R.drawable.mylocation);
                    txtLocation.setText("현재위치");
                    stopMyLocation();
                    mMapController.setMapCenter(new NGeoPoint(127.1480000, 35.8241930),12);
                    locationType = 0;
                }

            }
        });

        layoutProgressbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }


    //위치정보 동의 퍼미션 받기(앱 첫 시작후 지도 들어갈 시)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch(requestCode) {
            case LOCATION_REQUEST:
                if (canAccessLocation()) {//동의시
                    doLocationThing();
                }
                else {//비동의시
                    Toast.makeText(this, "위치 정보를 받아올 수 없습니다.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
        }else{
            return false;
        }
    }

    private void doLocationThing() {
        //Toast.makeText(this, "위치정보 활용에 동의하셨습니다.", Toast.LENGTH_SHORT).show();

        mMapController = mMapView.getMapController();

        mMapView.setApiKey(API_KEY); // 클라이언트 아이디 값 설정

        mMapView.setScalingFactor(2.0f);

        mMapView.setClickable(true);

        mMapView.setBuiltInZoomControls(true, null);

        mMapView.setOnMapStateChangeListener(this);


        nMapViewerResourceProvider = new NMapViewerResourceProvider(this);

        nMapOverlayManager = new NMapOverlayManager(this,mMapView,nMapViewerResourceProvider);

        int markerId = NMapPOIflagType.PIN;
        NMapPOIdata poiData = new NMapPOIdata(2,nMapViewerResourceProvider);
        poiData.beginPOIdata(10);

        for (int i = 0; i<datas.size();i++){
            double x = Double.parseDouble(datas.get(i).getX());
            double y = Double.parseDouble(datas.get(i).getY());
            int storeId = Integer.parseInt(datas.get(i).getStoreId());
            NMapPOIitem item = poiData.addPOIitem(y,x,datas.get(i).getName(),markerId,storeId);
            //item.setRightAccessory(true,NMapPOIflagType.CLICKABLE_ARROW);
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

    }


    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null){
            mMapController.setMapCenter(new NGeoPoint(127.1480000, 35.8241930),12);
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
        String name = "";
        String address = "";
        String price = "";
        String foodName = "";
        String tel = "";
        String img_url = "";
        Double x = 0.0;
        Double y = 0.0;

        for (int i=0;i<datas.size();i++){
            if (clickStore.equals(datas.get(i).getName())){
                ceoName = datas.get(i).getCeoName();
                name = datas.get(i).getName();
                address = datas.get(i).getAddress();
                price = datas.get(i).getPrice();
                foodName = datas.get(i).getFoodName();
                tel = datas.get(i).getTel();
                img_url = datas.get(i).getImg_url();
                x = Double.parseDouble(datas.get(i).getX());
                y = Double.parseDouble(datas.get(i).getY());

            }else{

            }
        }

        Logger.log(ceoName+"/"+name+"/"+address+"/"+price+"/"+foodName+"/"+tel+"/"+img_url);

        return new NMapCalloutCustomOverlayView(KindFoodMapActivity.this, nMapOverlay, nMapOverlayItem, rect, ceoName, name, address, price, foodName, tel, img_url, x, y);
    }

    @Override
    public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {}

    //마커 풍선말 클릭시 넘어가는 메소드
    @Override
    public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

        Intent intent = new Intent(KindFoodMapActivity.this, FoodStoreInfoActivity.class);
        intent.putExtra("datas", nMapPOIitem.getId());
        startActivity(intent);

    }


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
                    Toast.makeText(KindFoodMapActivity.this, "GPS를 활성화 해주세요.",
                            Toast.LENGTH_LONG).show();

                    return;
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


    public void setRoad(Double x, Double y, String name){

        String uri ="http://maps.google.com/maps?saddr="+myLocationX+","+myLocationY+"&daddr="+name+"&hl=ko";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(uri));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER );
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);

    }

    public void getStreetView(double x, double y){
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+x+","+y);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }



    /* MyLocation Listener */
    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

        @Override
        public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {


            if (mMapController != null) {
                if (initLocation == 0){
                    myLocationX = myLocation.getLatitude();
                    myLocationY = myLocation.getLongitude();
                    initLocation = 1;
                }else {
                    mMapController.setMapCenter(new NGeoPoint(myLocation.getLongitude(), myLocation.getLatitude()), 12);
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


}
