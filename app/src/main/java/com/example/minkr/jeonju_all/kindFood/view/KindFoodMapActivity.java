package com.example.minkr.jeonju_all.kindFood.view;

import android.Manifest;
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
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-10-26.
 */

public class KindFoodMapActivity extends NMapActivity implements NMapView.OnMapStateChangeListener {

//        private NMapView mMapView;
    private final String CLIENT_ID = "5lvyL1UwyDdfnK1Xxig6";
    private final String API_KEY = "E7NcpGo0wY";
    NMapLocationManager mMapLocationManager;
    NMapCompassManager mMapCompassManager;
    NMapMyLocationOverlay mMyLocationOverlay;
    NMapOverlayManager mOverlayManager;
    NMapController mMapController;

    @BindView(R.id.nMapVIew)
    NMapView mMapView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_food_map);
        ButterKnife.bind(this);
//        mMapView = new NMapView(KindFoodMapActivity.this);
//        setContentView(mMapView);
        mMapController = mMapView.getMapController();
        mMapView.setClientId(CLIENT_ID); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setBuiltInZoomControls(true, null);
        mMapView.setOnMapStateChangeListener(this);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();
    }

    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null) { // success
            mMapController.setMapCenter(
                    new NGeoPoint(126.978371, 37.5666091), 11);
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
}
