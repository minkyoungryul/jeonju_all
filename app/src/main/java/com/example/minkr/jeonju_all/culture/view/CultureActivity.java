package com.example.minkr.jeonju_all.culture.view;

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
import com.example.minkr.jeonju_all.culture.data.CultureListData;
import com.example.minkr.jeonju_all.culture.presenter.CulturePresenter;
import com.example.minkr.jeonju_all.custom.CultureShareDialog;
import com.example.minkr.jeonju_all.house.view.HouseStoreInfoActivity;
import com.example.minkr.jeonju_all.main.data.BookmarkList;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-11-07.
 */

public class CultureActivity extends AppCompatActivity implements CultureView{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ib_back)
    ImageButton ib_back;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @BindView(R.id.ib_map)
    ImageButton ib_map;

    LinearLayoutManager mLayoutManager;
    CultureAdapter adapter;
    List<CultureListData> datas= new ArrayList<>();

    CulturePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture);
        ButterKnife.bind(this);

        progress_bar.setVisibility(View.VISIBLE);
        presenter = new CulturePresenter();
        presenter.attachView(this);
        presenter.getCultureList();

        init();
        setListener();
    }

    private void init() {
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new CultureAdapter(this, datas, presenter);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        ib_back.setOnClickListener(v->finish());

        ib_map.setOnClickListener(v -> {
            Intent intent = new Intent(CultureActivity.this, CultureMapActivity.class);
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
    public void getCultureList(List<CultureListData> cultureListData) {

        String[] img_url = getResources().getStringArray(R.array.culture);
        for(int i=0; i<cultureListData.size(); i++){
            cultureListData.get(i).setImg_url(img_url[i]);
        }

        datas.addAll(cultureListData);
        adapter.notifyDataSetChanged();
        progress_bar.setVisibility(View.GONE);

        presenter.getCultureDB();
    }

    @Override
    public void showInfo(CultureListData data) {
        Intent intent = new Intent(CultureActivity.this, HouseStoreInfoActivity.class);
        intent.putExtra("data", data.getUserHomepage());
        intent.putExtra("type","문화");
        startActivity(intent);
    }

    @Override
    public void getAddressClick(CultureListData data) {
        Intent intent = new Intent(CultureActivity.this, CultureMap2Activity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void getCultureDBData(List<BookmarkList> bookmarkLists) {
        for(int i=0; i<datas.size(); i++){
            for(int j=0; j<bookmarkLists.size(); j++){
                if(datas.get(i).getTitle().equals(bookmarkLists.get(j).getTitle())){
                    datas.get(i).setLike(true);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void isLikeChangeData(CultureListData cultureListData) {
        for(int i=0; i<datas.size(); i++){
            if(datas.get(i).equals(cultureListData.getTitle())){
                datas.get(i).setLike(cultureListData.isLike());
            }
        }
        adapter.notifyDataSetChanged();

        if(cultureListData.isLike())
            Toast.makeText(getContext(), "즐겨찾기 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "즐겨찾기 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    CultureShareDialog shareDialog;
    @Override
    public void showDialog(CultureListData data) {
        shareDialog = new CultureShareDialog(this, data);
        shareDialog.show();
    }
}
