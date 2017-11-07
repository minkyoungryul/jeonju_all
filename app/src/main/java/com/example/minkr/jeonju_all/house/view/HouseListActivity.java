package com.example.minkr.jeonju_all.house.view;

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
import com.example.minkr.jeonju_all.house.data.HouseListData;
import com.example.minkr.jeonju_all.house.presenter.HousePresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-06.
 */

public class HouseListActivity extends AppCompatActivity implements HouseView{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    HouseAdapter adapter;
    LinearLayoutManager mLayoutManager;
    List<HouseListData> datas = new ArrayList<>();

    HousePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_list);
        ButterKnife.bind(this);

        presenter = new HousePresenter();
        presenter.attachView(this);
        presenter.getHouseList();

        init();
        setListener();
    }

    private void init() {
        progressBar.setVisibility(View.VISIBLE);
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new HouseAdapter(this, datas, presenter);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());
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
    public void getHouseList(List<HouseListData> houseListData) {
        String[] img_url = getResources().getStringArray(R.array.house);
        for(int i=0; i<houseListData.size(); i++) {
            houseListData.get(i).setImg_url(img_url[i]);
        }

        progressBar.setVisibility(View.GONE);
        datas.addAll(houseListData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getStoreInfo(HouseListData data) {
        Intent intent = new Intent(HouseListActivity.this, HouseStoreInfoActivity.class);
        intent.putExtra("data", data.getHomepage());
        startActivity(intent);
    }
}
