package com.example.minkr.jeonju_all.culture.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.culture.data.CultureListData;
import com.example.minkr.jeonju_all.culture.presenter.CulturePresenter;

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
        adapter = new CultureAdapter(this, datas);

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
    public void getCultureList(List<CultureListData> cultureListData) {

        String[] img_url = getResources().getStringArray(R.array.culture);
        for(int i=0; i<cultureListData.size(); i++){
            cultureListData.get(i).setImg_url(img_url[i]);
        }

        datas.addAll(cultureListData);
        adapter.notifyDataSetChanged();
        progress_bar.setVisibility(View.GONE);
    }
}
