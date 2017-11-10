package com.example.minkr.jeonju_all.food.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.food.presenter.FoodDetailPresenter;
import com.example.minkr.jeonju_all.kindFood.view.FoodStoreInfoActivity;
import com.example.minkr.jeonju_all.util.Logger;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-03.
 */

public class FoodDetailActivity extends AppCompatActivity implements FoodDetailView{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.ib_map)
    ImageButton ib_map;

    List<FoodListData> datas;
    LinearLayoutManager mLayoutManager;
    FoodDetailAdapter adapter;

    FoodDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        presenter = new FoodDetailPresenter();
        presenter.attachView(this);

        Intent intent = getIntent();
        datas = (List<FoodListData>) intent.getSerializableExtra("data");
        Logger.log("#12 datas->"+datas.toString());
        init();
        setListener();
    }

    private void init() {
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new FoodDetailAdapter(this, datas, presenter);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());

        ib_map.setOnClickListener(v -> {
            Intent intent = new Intent(FoodDetailActivity.this, FoodMap2Activity.class);
            intent.putExtra("data", (Serializable) datas);
            startActivity(intent);
        });
    }

    @Override
    public void getStoreInfo(FoodListData data) {
        if (data.getStoreId() == null || data.getStoreId().equals("0")){
            Toast.makeText(this, "상세 정보가 없습니다.", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(FoodDetailActivity.this, FoodStoreInfoActivity.class);
            intent.putExtra("storeId", data.getStoreId());
            startActivity(intent);
        }
    }

    @Override
    public void getAddressClick(FoodListData data){
        Intent intent = new Intent(FoodDetailActivity.this, FoodMap3Activity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void notConnectNetworking() {

    }
}
