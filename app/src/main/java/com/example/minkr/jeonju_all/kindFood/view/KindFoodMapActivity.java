package com.example.minkr.jeonju_all.kindFood.view;

import android.Manifest;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

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
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
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

public class KindFoodMapActivity extends NMapActivity implements NMapView.OnMapStateChangeListener,NMapView.OnMapViewTouchEventListener,NMapOverlayManager.OnCalloutOverlayListener {

//        private NMapView mMapView;
    private final String CLIENT_ID = "5lvyL1UwyDdfnK1Xxig6";
    private final String API_KEY = "E7NcpGo0wY";
    NMapController mMapController = null;
    NMapViewerResourceProvider nMapViewerResourceProvider = null;
    NMapOverlayManager nMapOverlayManager;
    NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = null;
    NMapPOIdata nMapPOIdata;


    @BindView(R.id.nMapVIew)
    NMapView mMapView = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_food_map);
        ButterKnife.bind(this);

        mMapController = mMapView.getMapController();
        mMapView.setClientId(CLIENT_ID); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setBuiltInZoomControls(true, null);
        mMapView.setOnMapStateChangeListener(this);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();

        nMapViewerResourceProvider = new NMapViewerResourceProvider(this);

        nMapOverlayManager = new NMapOverlayManager(this, mMapView, nMapViewerResourceProvider);

        int markerId = NMapPOIflagType.PIN;

        nMapPOIdata = new NMapPOIdata(2,nMapViewerResourceProvider);
        nMapPOIdata.beginPOIdata(2);

        nMapPOIdata.addPOIitem(127.0630205,37.5091300,"pizza",markerId,10);
        nMapPOIdata.addPOIitem(127.061,37.51,"pizza2",markerId,0);
        nMapPOIdata.endPOIdata();

        NMapPOIdataOverlay poiDataOverlay = nMapOverlayManager.createPOIdataOverlay(nMapPOIdata,null);

        poiDataOverlay.showAllPOIdata(0);

        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);

        nMapOverlayManager.setOnCalloutOverlayListener((NMapOverlayManager.OnCalloutOverlayListener)this);

    }

    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null) { // success
            mMapController.setMapCenter(
                    new NGeoPoint(127.0630200, 37.5091300), 25);
        } else { // fail
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
}
