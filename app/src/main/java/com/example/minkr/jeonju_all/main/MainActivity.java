package com.example.minkr.jeonju_all.main;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.kindFood.view.KindFoodActivity;
import com.example.minkr.jeonju_all.main.view.MainBookmarkFrag;
import com.example.minkr.jeonju_all.main.view.MainHomeFrag;
import com.example.minkr.jeonju_all.main.view.MainSettingFrag;
import com.example.minkr.jeonju_all.parking.view.ParkingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


//    SlidingMenu slidingMenu;
//    MenuFragment menuFragment;

    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        initSlideMenu();
        init();
        setListener();
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
        spec.setIndicator(createTabView(R.drawable.selector_bookmark, "최근 이용"));
        mTabHost.addTab(spec, MainBookmarkFrag.class, null);

        spec = mTabHost.newTabSpec("settings");
        spec.setContent(tag -> {
            return findViewById(android.R.id.tabcontent);
        });
        spec.setIndicator(createTabView(R.drawable.selector_setting, "설정"));
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

   /* private void initSlideMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setSlidingEnabled(false);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.menu_frame);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, menuFragment = MenuFragment.newInstance())
                .commitAllowingStateLoss();
    }*/

   private void setListener() {

//        ib_menu.setOnClickListener(v->{
//            slidingMenu.showMenu();
//        });
    }
}
