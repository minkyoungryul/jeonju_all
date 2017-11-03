package com.example.minkr.jeonju_all.food.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.food.presenter.FoodPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-02.
 */

public class FoodActivity extends AppCompatActivity implements FoodView{

    @BindView(R.id.rice_recyclerView)
    RecyclerView rice_recyclerView;

    @BindView(R.id.bibimbap_recyclerView)
    RecyclerView bibimbap_recyclerView;

    @BindView(R.id.kongbap_recyclerView)
    RecyclerView kongbap_recyclerView;

    @BindView(R.id.wine_recyclerView)
    RecyclerView wine_recyclerView;

    @BindView(R.id.hanok_recyclerView)
    RecyclerView hanok_recyclerView;

    LinearLayoutManager mLayoutManager;
    LinearLayoutManager mLayoutManager2;
    LinearLayoutManager mLayoutManager3;
    LinearLayoutManager mLayoutManager4;
    LinearLayoutManager mLayoutManager5;

    FoodRiceAdapter rice_adapter;
    FoodBibimbapAdapter bibimbap_adapter;
    FoodKongbapAdapter kongbap_adapter;
    FoodWineAdapter wine_adapter;
    FoodHanokAdapter hanok_adapter;

    List<FoodListData> riceList = new ArrayList<>();
    List<FoodListData> all_riceList = new ArrayList<>();
    List<FoodListData> bibimbapList = new ArrayList<>();
    List<FoodListData> all_bibimbapList = new ArrayList<>();
    List<FoodListData> kongbapList = new ArrayList<>();
    List<FoodListData> all_kongbapList = new ArrayList<>();
    List<FoodListData> wineList = new ArrayList<>();
    List<FoodListData> all_wineList = new ArrayList<>();
    List<FoodListData> hanokList = new ArrayList<>();
    List<FoodListData> all_hanokList = new ArrayList<>();

    FoodPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ButterKnife.bind(this);

        presenter = new FoodPresenter();
        presenter.attachView(this);

        presenter.getFoodRiceList();
        presenter.getFoodBibimbapList();
        presenter.getFoodKongbapList();
        presenter.getFoodWineList();
        presenter.getFoodHanokList();

        init();
        setListener();
    }

    private void init() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager5 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rice_adapter = new FoodRiceAdapter(this, riceList);
        rice_recyclerView.setLayoutManager(mLayoutManager);
        rice_recyclerView.setAdapter(rice_adapter);

        bibimbap_adapter = new FoodBibimbapAdapter(this, bibimbapList);
        bibimbap_recyclerView.setLayoutManager(mLayoutManager2);
        bibimbap_recyclerView.setAdapter(bibimbap_adapter);

        kongbap_adapter = new FoodKongbapAdapter(this, kongbapList);
        kongbap_recyclerView.setLayoutManager(mLayoutManager3);
        kongbap_recyclerView.setAdapter(kongbap_adapter);

        wine_adapter = new FoodWineAdapter(this, wineList);
        wine_recyclerView.setLayoutManager(mLayoutManager4);
        wine_recyclerView.setAdapter(wine_adapter);

        hanok_adapter = new FoodHanokAdapter(this, hanokList);
        hanok_recyclerView.setLayoutManager(mLayoutManager5);
        hanok_recyclerView.setAdapter(hanok_adapter);
    }

    private void setListener() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void notConnectNetworking() {}

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void getFoodRiceDatas(List<FoodListData> foodListData) {
        all_riceList.addAll(foodListData);
        for(int i=0; i<5; i++){
            riceList.add(foodListData.get(i));
        }
        rice_adapter.notifyDataSetChanged();
    }

    @Override
    public void getFoodBibimbapDatas(List<FoodListData> foodListData) {
        all_bibimbapList.addAll(foodListData);
        for(int i=0; i<5; i++){
            bibimbapList.add(foodListData.get(i));
        }
        bibimbap_adapter.notifyDataSetChanged();
    }

    @Override
    public void getFoodKongbapDatas(List<FoodListData> foodListData) {
        all_kongbapList.addAll(foodListData);
        for(int i=0; i<5; i++){
            kongbapList.add(foodListData.get(i));
        }
        kongbap_adapter.notifyDataSetChanged();
    }

    @Override
    public void getFoodWineDatas(List<FoodListData> foodListData) {
        all_wineList.addAll(foodListData);
        for(int i=0; i<5; i++){
            wineList.add(foodListData.get(i));
        }
        wine_adapter.notifyDataSetChanged();
    }

    @Override
    public void getFoodHanokDatas(List<FoodListData> foodListData) {
        all_hanokList.addAll(foodListData);
        for(int i=0; i<5; i++){
            hanokList.add(foodListData.get(i));
        }
        hanok_adapter.notifyDataSetChanged();
    }
}
