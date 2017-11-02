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

    LinearLayoutManager mLayoutManager;
    FoodRiceAdapter rice_adapter;
    List<FoodListData> riceList = new ArrayList<>();

    FoodPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        ButterKnife.bind(this);

        presenter = new FoodPresenter();
        presenter.attachView(this);

        presenter.getFoodRiceList();
        init();
        setListener();
    }

    private void init() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rice_adapter = new FoodRiceAdapter(this, riceList);
        rice_recyclerView.setLayoutManager(mLayoutManager);
        rice_recyclerView.setAdapter(rice_adapter);
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
        riceList.addAll(foodListData);
        rice_adapter.notifyDataSetChanged();
    }
}
