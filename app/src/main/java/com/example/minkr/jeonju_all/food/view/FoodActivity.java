package com.example.minkr.jeonju_all.food.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.food.presenter.FoodPresenter;
import com.example.minkr.jeonju_all.kindFood.view.FoodStoreInfoActivity;

import java.io.Serializable;
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

    @BindView(R.id.ll_rice_show)
    LinearLayout ll_rice_show;

    @BindView(R.id.ll_bibimbap_show)
    LinearLayout ll_bibimbap_show;

    @BindView(R.id.ll_hanok_show)
    LinearLayout ll_hanok_show;

    @BindView(R.id.ll_wine_show)
    LinearLayout ll_wine_show;

    @BindView(R.id.ll_kongbap_show)
    LinearLayout ll_kongbap_show;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.ib_map)
    ImageButton ib_map;

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

        rice_adapter = new FoodRiceAdapter(this, riceList, presenter);
        rice_recyclerView.setLayoutManager(mLayoutManager);
        rice_recyclerView.setAdapter(rice_adapter);

        bibimbap_adapter = new FoodBibimbapAdapter(this, bibimbapList, presenter);
        bibimbap_recyclerView.setLayoutManager(mLayoutManager2);
        bibimbap_recyclerView.setAdapter(bibimbap_adapter);

        kongbap_adapter = new FoodKongbapAdapter(this, kongbapList, presenter);
        kongbap_recyclerView.setLayoutManager(mLayoutManager3);
        kongbap_recyclerView.setAdapter(kongbap_adapter);

        wine_adapter = new FoodWineAdapter(this, wineList, presenter);
        wine_recyclerView.setLayoutManager(mLayoutManager4);
        wine_recyclerView.setAdapter(wine_adapter);

        hanok_adapter = new FoodHanokAdapter(this, hanokList, presenter);
        hanok_recyclerView.setLayoutManager(mLayoutManager5);
        hanok_recyclerView.setAdapter(hanok_adapter);
    }

    private void setListener() {
        ll_rice_show.setOnClickListener(v->{
            nextActivity(all_riceList);
        });

        ll_bibimbap_show.setOnClickListener(v->{
            nextActivity(all_bibimbapList);
        });

        ll_hanok_show.setOnClickListener(v->{
            nextActivity(all_hanokList);
        });

        ll_kongbap_show.setOnClickListener(v->{
            nextActivity(all_kongbapList);
        });

        ll_wine_show.setOnClickListener(v->{
            nextActivity(all_wineList);
        });

        ib_back.setOnClickListener(v->finish());

        ib_map.setOnClickListener(v -> {
            Intent intent = new Intent(FoodActivity.this, FoodMapActivity.class);
            intent.putExtra("data1", (Serializable) all_riceList);
            intent.putExtra("data2", (Serializable) all_bibimbapList);
            intent.putExtra("data3", (Serializable) all_hanokList);
            intent.putExtra("data4", (Serializable) all_kongbapList);
            intent.putExtra("data5", (Serializable) all_wineList);
            startActivity(intent);
        });
    }

    public void nextActivity(List<FoodListData> datas){
        Intent intent = new Intent(FoodActivity.this, FoodDetailActivity.class);
        intent.putExtra("data", (Serializable) datas);
        startActivity(intent);
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

    public void getFoodDatas(String[] storeId, List<FoodListData> datas, List<FoodListData> foodListData, List<FoodListData> all_foodListData){
        for(int i=0; i<datas.size(); i++){
            datas.get(i).setStoreId(storeId[i]);
        }
        all_foodListData.addAll(datas);
        for(int i=0; i<5; i++){
            foodListData.add(datas.get(i));
        }
    }

    @Override
    public void getFoodRiceDatas(List<FoodListData> foodListData) {
        String[] storeId = {"21037888", "13447430", "11555125", "34827805", "11522746", "11847873", "0", "12012617", "11874167", "11720563","11782597","11707569","11658337"};
        getFoodDatas(storeId, foodListData, riceList, all_riceList);
        rice_adapter.notifyDataSetChanged();
    }

    @Override
    public void getFoodBibimbapDatas(List<FoodListData> foodListData) {
        String[] storeId = {"35749244", "11707231", "36410653", "11710588", "11627365", "11728244", "16795216"};
        getFoodDatas(storeId, foodListData, bibimbapList, all_bibimbapList);
        bibimbap_adapter.notifyDataSetChanged();
    }

    @Override
    public void getFoodKongbapDatas(List<FoodListData> foodListData) {
        String[] storeId = {"31498549", "31991353", "38327383", "36601205", "32236577", "33418635", "37106493", "13548405", "17453618", "11623233","17077028","21885584","17447792","16809544", "11712007","34454016","20027334", "12991219","437372678", "16786221","11658492","13317872"};
        getFoodDatas(storeId, foodListData, kongbapList, all_kongbapList);
        kongbap_adapter.notifyDataSetChanged();
    }

    @Override
    public void getFoodWineDatas(List<FoodListData> foodListData) {
        String[] storeId = {"37589832", "38337667", "11886663", "16881956", "21007374"};
        getFoodDatas(storeId,foodListData, wineList, all_wineList);
        wine_adapter.notifyDataSetChanged();
    }

    @Override
    public void getFoodHanokDatas(List<FoodListData> foodListData) {
        String[] storeId = {"13141208", "36438524", "36601205", "35494447", "36038864", "38275608", "0", "38275894", "16808637", "0","13525327", "36864799", "0", "35562901", "13045686", "21039411", "0", "33876992", "19918553",
                "34827805", "19574787", "16808444", "20364561", "16795271", "37027890", "16793271", "11603316", "33276367", "13342931", "13040824", "394986323", "201840183", "13080397", "11874167", "33875242", "16808573", "36383569",
                "0", "0", "20174409", "11877554", "16809544", "17448890", "11540579", "11782597", "11717479", "16795216"};
        getFoodDatas(storeId, foodListData, hanokList, all_hanokList);
        hanok_adapter.notifyDataSetChanged();
    }

    @Override
    public void getStoreInfo(FoodListData data) {
        if (data.getStoreId() == null || data.getStoreId().equals("0")){
            Toast.makeText(this, "상세 정보가 없습니다.", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(FoodActivity.this, FoodStoreInfoActivity.class);
            intent.putExtra("storeId", data.getStoreId());
            startActivity(intent);
        }
    }
}
