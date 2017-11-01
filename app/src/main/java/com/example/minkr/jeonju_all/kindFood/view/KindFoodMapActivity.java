package com.example.minkr.jeonju_all.kindFood.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.minkr.jeonju_all.R;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-10-26.
 */

public class KindFoodMapActivity extends NMapActivity implements OnMapStateChangeListener,OnMapViewTouchEventListener,
        NMapOverlayManager.OnCalloutOverlayListener,NMapPOIdataOverlay.OnStateChangeListener{

    //private final String CLIENT_ID = "5lvyL1UwyDdfnK1Xxig6";
    //private final String API_KEY = "E7NcpGo0wY";

    private final String API_KEY = "5lvyL1UwyDdfnK1Xxig6";
    //NMapView mMapView = null;
    NMapController mMapController = null;


    NMapViewerResourceProvider nMapViewerResourceProvider = null;
    NMapOverlayManager nMapOverlayManager;
    //NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = null;

    @BindView(R.id.mapView)
    NMapView mMapView = null;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_food_map);
        ButterKnife.bind(this);


        mMapController = mMapView.getMapController();

        mMapView.setApiKey(API_KEY); // 클라이언트 아이디 값 설정

        mMapView.setScalingFactor(2.0f);

        mMapView.setClickable(true);

        mMapView.setBuiltInZoomControls(true, null);

        mMapView.setOnMapStateChangeListener(this);



        nMapViewerResourceProvider = new NMapViewerResourceProvider(this);

        nMapOverlayManager = new NMapOverlayManager(this,mMapView,nMapViewerResourceProvider);

        //nMapOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener);

        int markerId = NMapPOIflagType.PIN;
        NMapPOIdata poiData = new NMapPOIdata(2,nMapViewerResourceProvider);
        poiData.beginPOIdata(2);
        poiData.addPOIitem(127.1170040,35.8032230,"만나별",markerId,0);
        poiData.addPOIitem(127.1485280,35.8176730,"이래면옥",markerId,1);
        poiData.endPOIdata();
        NMapPOIdataOverlay poiDataOverlay = nMapOverlayManager.createPOIdataOverlay(poiData,null);

        poiDataOverlay.showAllPOIdata(0);
        poiDataOverlay.setOnStateChangeListener((NMapPOIdataOverlay.OnStateChangeListener)this);
        //poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);

        nMapOverlayManager.setOnCalloutOverlayListener((NMapOverlayManager.OnCalloutOverlayListener)this);


    }


    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null){
            mMapController.setMapCenter(new NGeoPoint(127.1480000, 35.8241930),12);
        }else{
            //35.8241930
            //127.1480000
        }
    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

    }


    @Override
    public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onLongPressCanceled(NMapView nMapView) {

    }

    @Override
    public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {

    }

    @Override
    public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {
        return new NMapCalloutBasicOverlay(nMapOverlay,nMapOverlayItem,rect);
    }

    @Override
    public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

    }

    @Override
    public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

        Intent intent = new Intent(KindFoodMapActivity.this, FoodStoreInfoActivity.class);
        //intent.putExtra("data", data);
        startActivity(intent);

        //Toast.makeText(KindFoodMapActivity.this,"onCalloutClick: "+nMapPOIitem.getTitle()+" //"+nMapPOIitem.getId(),Toast.LENGTH_LONG).show();
    }
}
