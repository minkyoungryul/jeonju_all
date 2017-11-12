package com.example.minkr.jeonju_all.main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.kindFood.view.KindFoodActivity;
import com.example.minkr.jeonju_all.main.presenter.MainPresenter;
import com.example.minkr.jeonju_all.main.view.MainBookmarkFrag;
import com.example.minkr.jeonju_all.main.view.MainHomeFrag;
import com.example.minkr.jeonju_all.main.view.MainSettingFrag;
import com.example.minkr.jeonju_all.main.view.MainView;
import com.example.minkr.jeonju_all.util.Logger;
import com.nhn.android.maps.NMapLocationManager;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class MainActivity extends AppCompatActivity{


    FragmentTabHost mTabHost;
    //위치정보 허가
    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int LOCATION_REQUEST=3;

    NMapLocationManager nMapLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
        setListener();

        nMapLocationManager = new NMapLocationManager(this);

        setCheckLocation();

    }

    private void init() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        FragmentTabHost.TabSpec spec;
        spec = mTabHost.newTabSpec("home");
        spec.setContent(tag -> {
            return findViewById(android.R.id.tabcontent);
        });
        spec.setIndicator(createTabView(R.drawable.selector_home, "홈"));
        mTabHost.addTab(spec, MainHomeFrag.class,null);

        spec = mTabHost.newTabSpec("bookmark");
        spec.setContent(tag -> {
            return findViewById(android.R.id.tabcontent);
        });
        spec.setIndicator(createTabView(R.drawable.selector_bookmark, "즐겨찾기"));
        mTabHost.addTab(spec, MainBookmarkFrag.class, null);

        spec = mTabHost.newTabSpec("settings");
        spec.setContent(tag -> {
            return findViewById(android.R.id.tabcontent);
        });
        spec.setIndicator(createTabView(R.drawable.selector_setting, "더보기"));
        mTabHost.addTab(spec, MainSettingFrag.class, null);
    }

    private View createTabView(final int id, final String text) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabs_icon, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageDrawable(getResources().getDrawable(id));
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        textView.setText(text);
        return view;
    }

    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Logger.log("Unable to get MessageDigest. signature=" + signature);
            }
        }
        return null;
    }

    public void setCheckLocation(){
        if (canAccessLocation()) {
            //setCheckMap();
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
            }
        }

    }
    /*

    public void setCheckMap(){
        boolean isMyLocationEnabled = nMapLocationManager.enableMyLocation(true);
        if (!isMyLocationEnabled) {

            setAlertDialogShow();

            Toast.makeText(MainActivity.this, "위치 정보를 받기위해 On 해주세요.",
                    Toast.LENGTH_LONG).show();

            return;
        }
    }
    */

    //위치정보 동의 퍼미션 받기(앱 첫 시작후 지도 들어갈 시)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch(requestCode) {
            case LOCATION_REQUEST:
                if (canAccessLocation()) {//동의시
                    //setCheckMap();
                }
                else {//비동의시
                    Toast.makeText(this, "위치정보를 동의해주세요.", Toast.LENGTH_LONG).show();
                    finish();
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


   private void setListener() {
    }

    private Toast toast;
    private long backKeyPressedTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
