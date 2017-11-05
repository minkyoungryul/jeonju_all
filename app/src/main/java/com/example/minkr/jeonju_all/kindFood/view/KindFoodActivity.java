package com.example.minkr.jeonju_all.kindFood.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;
import com.example.minkr.jeonju_all.kindFood.presenter.KindFoodPresenter;
import com.example.minkr.jeonju_all.util.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-10-22.
 */

public class KindFoodActivity extends AppCompatActivity implements KindFoodVIew{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.ib_map)
    ImageButton ib_map;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    LinearLayoutManager mLayoutManager;
    KindFoodAdapter adapter;
    KindFoodPresenter presenter;

    List<KindFoodListData> kindFoodList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_food);
        ButterKnife.bind(this);

        presenter = new KindFoodPresenter();
        presenter.attachView(this);

        init();
        setListener();
        presenter.getKindFoodList();
    }

    private void setListener() {
        ib_back.setOnClickListener(v->{
            finish();
        });

        ib_map.setOnClickListener(v->{
            Intent intent = new Intent(KindFoodActivity.this, KindFoodMapActivity.class);
            intent.putExtra("data", (Serializable) kindFoodList);
            startActivity(intent);
        });
    }

    private void init() {
        progressBar.setVisibility(View.VISIBLE);
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new KindFoodAdapter(this,kindFoodList,presenter);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void notConnectNetworking() {

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void getKindFoodDatas(List<KindFoodListData> kindFoodListDatas) {
        int[] store_ids = {16953705, 16784799, 17115770, 34005058, 16986870, 11710472, 19555701, 16985833, 36004947, 36004175};
        for(int i=0; i<kindFoodListDatas.size(); i++){
            kindFoodListDatas.get(i).setStoreId(store_ids[i]+"");
        }

        kindFoodList.addAll(kindFoodListDatas);
        Logger.log("#6 kindFoodList ->"+ kindFoodList.toString());
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getFoodStoreInfo(KindFoodListData data) {
        Intent intent = new Intent(KindFoodActivity.this, FoodStoreInfoActivity.class);
        intent.putExtra("storeId", data.getStoreId());
        intent.putExtra("datas",0);
        startActivity(intent);
    }
}
