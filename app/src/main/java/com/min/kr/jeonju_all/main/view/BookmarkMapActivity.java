package com.min.kr.jeonju_all.main.view;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.kindFood.view.map.NMapPOIflagType;
import com.min.kr.jeonju_all.kindFood.view.map.NMapViewerResourceProvider;
import com.min.kr.jeonju_all.main.data.BookmarkList;
import com.min.kr.jeonju_all.main.view.map.NMapCalloutCustomOverlayView;
import com.min.kr.jeonju_all.util.Logger;
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

import butterknife.BindView;
import butterknife.ButterKnife;

;

/**
 * Created by minkr on 2017-10-26.
 */

public class BookmarkMapActivity extends NMapActivity implements OnMapStateChangeListener,OnMapViewTouchEventListener,
        NMapOverlayManager.OnCalloutOverlayViewListener,NMapPOIdataOverlay.OnStateChangeListener{

    private final String API_KEY = "5lvyL1UwyDdfnK1Xxig6";
    NMapController mMapController = null;

    NMapViewerResourceProvider nMapViewerResourceProvider = null;
    com.min.kr.jeonju_all.food.view.map.NMapViewerResourceProvider nMapViewerResourceProvider2 = null;
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

    int locationType = 0;
    double myLocationX = 0.0;
    double myLocationY = 0.0;

    Double x = 0.0;
    Double y = 0.0;

    BookmarkList data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_food_map);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        data = (BookmarkList) intent.getSerializableExtra("data");

        Logger.log("#80 datas -> "+data);

        init();
        setListener();

    }

    public void init(){
        doLocationThing();

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
                        if (data.getType().toString().equals("모범업소")){
                            mMapController.setMapCenter(new NGeoPoint(Double.parseDouble(data.getPosY()), Double.parseDouble(data.getPosX())), 12);
                        }else{
                            mMapController.setMapCenter(new NGeoPoint(Double.parseDouble(data.getPosX()), Double.parseDouble(data.getPosY())), 12);
                        }
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
            poiData = new NMapPOIdata(1,nMapViewerResourceProvider);
            poiData.beginPOIdata(1);


            if (data.getType().toString().equals("모범업소")){
                Logger.log("#80 모범 : "+data.getPosY()+" / "+data.getPosX());
                double x = Double.parseDouble(data.getPosY());
                double y = Double.parseDouble(data.getPosX());
                poiData.addPOIitem(x,y,data.getTitle(),markerId,1);
            }else {
                Logger.log("#80 음식 : "+data.getPosX()+" / "+data.getPosY());
                double x = Double.parseDouble(data.getPosX());
                double y = Double.parseDouble(data.getPosY());
                poiData.addPOIitem(x,y,data.getTitle(),markerId,1);
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
            if (data.getType().toString().equals("모범업소")){
                mMapController.setMapCenter(new NGeoPoint(Double.parseDouble(data.getPosY()), Double.parseDouble(data.getPosX())), 12);
            }else{
                mMapController.setMapCenter(new NGeoPoint(Double.parseDouble(data.getPosX()), Double.parseDouble(data.getPosY())), 12);
            }
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

        String name = data.getTitle();
        String address = data.getAddress();
        String tel = data.getTel();
        String homepage = data.getHomepage_url();
        String img_url = data.getImg_url();
        if (data.getType().toString().equals("모범업소")){
            x = Double.parseDouble(data.getPosY());
            y = Double.parseDouble(data.getPosX());
        }else{
            x = Double.parseDouble(data.getPosX());
            y = Double.parseDouble(data.getPosY());
        }


        return new NMapCalloutCustomOverlayView(BookmarkMapActivity.this, nMapOverlay, nMapOverlayItem, rect, name, address, tel, homepage, img_url, x, y, 1);

    }

    @Override
    public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {}

    //마커 풍선말 클릭시 넘어가는 메소드
    @Override
    public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

        Logger.log("#66 poiitem -> "+nMapPOIitem.getId());

        Intent intent = new Intent(BookmarkMapActivity.this, BookmarkDetailActivity.class);
        intent.putExtra("data", data.getHomepage_url());
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

                    setAlertDialogShow();

                    return;
                }else{
                    layoutProgressbar.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    imgbtLocation.setImageResource(R.drawable.jeonjulocation);
                    imgbtLocation.setBackgroundResource(R.drawable.jeonjulocation);
                    txtLocation.setText("전주위치");
                    Toast.makeText(BookmarkMapActivity.this,"현재위치를 찾고 있습니다...",Toast.LENGTH_LONG).show();
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
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+y+","+x);
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
