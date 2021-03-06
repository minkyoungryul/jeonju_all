package com.min.kr.jeonju_all.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.culture.view.CultureActivity;
import com.min.kr.jeonju_all.facility.view.FacilityActivity;
import com.min.kr.jeonju_all.food.view.FoodActivity;
import com.min.kr.jeonju_all.house.view.HouseListActivity;
import com.min.kr.jeonju_all.kindFood.view.KindFoodActivity;
import com.min.kr.jeonju_all.main.data.BookmarkList;
import com.min.kr.jeonju_all.main.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-10-24.
 */

public class MainHomeFrag extends Fragment implements MainView{

    @BindView(R.id.rl_kind_food)
    RelativeLayout rl_kind_food;

//    @BindView(R.id.rl_parking)
//    RelativeLayout rl_parking;

    @BindView(R.id.rl_food)
    RelativeLayout rl_food;

    @BindView(R.id.rl_house)
    RelativeLayout rl_house;

    @BindView(R.id.rl_culture)
    RelativeLayout rl_culture;

    @BindView(R.id.rl_facilities)
    RelativeLayout rl_facilities;

    MainPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new MainPresenter();
        presenter.attachView(this);

        init();
        setListener();
    }

    private void init() {

    }

    private void setListener() {
        rl_kind_food.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), KindFoodActivity.class);
            startActivity(intent);
        });

        rl_food.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), FoodActivity.class);
            startActivity(intent);
        });

        rl_house.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), HouseListActivity.class);
            startActivity(intent);
        });

        rl_culture.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), CultureActivity.class);
            startActivity(intent);
        });

        rl_facilities.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), FacilityActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void notConnectNetworking() {

    }

    @Override
    public void getBookmarkList(List<BookmarkList> bookmarkLists) {

    }

    @Override
    public void showStoreInfo(BookmarkList data) {

    }

    @Override
    public void allDeleteData() {

    }

    @Override
    public void showDeleteDialog(BookmarkList data, int position) {

    }

    @Override
    public void deleteData(BookmarkList bookmarkList, int position) {

    }

    @Override
    public void getAddressClick(BookmarkList data) {

    }

    @Override
    public void showDialog(BookmarkList data) {

    }
}
