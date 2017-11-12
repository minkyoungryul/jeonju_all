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
import android.widget.Toast;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.custom.HouseShareDialog;
import com.example.minkr.jeonju_all.house.data.HouseListData;
import com.example.minkr.jeonju_all.house.presenter.HousePresenter;
import com.example.minkr.jeonju_all.main.data.BookmarkList;
import com.example.minkr.jeonju_all.util.Logger;
import com.tsengvn.typekit.TypekitContextWrapper;

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

    @BindView(R.id.ib_map)
    ImageButton ib_map;

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

        ib_map.setOnClickListener(v -> {
            Intent intent = new Intent(HouseListActivity.this, HouseMapActivity.class);
            intent.putExtra("data", (Serializable) datas);
            startActivity(intent);
        });
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
        presenter.getHouseDBData();
    }

    @Override
    public void getStoreInfo(HouseListData data) {
        Intent intent = new Intent(HouseListActivity.this, HouseStoreInfoActivity.class);
        intent.putExtra("data", data.getHomepage());
        intent.putExtra("type","숙박");
        startActivity(intent);
    }

    @Override
    public void getAddressClick(HouseListData data) {
        Intent intent = new Intent(HouseListActivity.this, HouseMap2Activity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void getHouseDBData(List<BookmarkList> bookmarkLists) {
        Logger.log("#45 bookmarkList ->"+bookmarkLists.toString());
        for(int i=0; i<datas.size(); i++){
            for(int j=0; j<bookmarkLists.size(); j++){
                if(bookmarkLists.get(j).getTitle().equals(datas.get(i).getStoreName())){
                    datas.get(i).setLike(true);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void isLikeChangeData(HouseListData houseListData) {
        for(int i=0; i<datas.size(); i++){
            if(datas.get(i).getStoreName().equals(houseListData.getStoreName())){
                datas.get(i).setLike(houseListData.isLike());
                Logger.log("#19 change kindFoodList->"+houseListData.toString());
            }
        }
        adapter.notifyDataSetChanged();

        if(houseListData.isLike())
            Toast.makeText(getContext(), "즐겨찾기 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "즐겨찾기 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    HouseShareDialog shareDialog;
    @Override
    public void showDialog(HouseListData data) {
        shareDialog = new HouseShareDialog(this, data);
        shareDialog.show();
    }
}
