package com.min.kr.jeonju_all.food.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.custom.FoodShareDialog;
import com.min.kr.jeonju_all.food.data.FoodListData;
import com.min.kr.jeonju_all.food.presenter.FoodDetailPresenter;
import com.min.kr.jeonju_all.kindFood.view.FoodStoreInfoActivity;
import com.min.kr.jeonju_all.util.Logger;
import com.tsengvn.typekit.TypekitContextWrapper;

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

    @BindView(R.id.tv_food_detail)
    TextView tv_food_detail;

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
        tv_food_detail.setText(datas.get(0).getType());

        mLayoutManager = new LinearLayoutManager(this);
        adapter = new FoodDetailAdapter(this, datas, presenter);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        ib_back.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.putExtra("datas", (Serializable) datas);
            if(datas.get(0).getType().equals("한정식")){
                setResult(100, intent);
            }else if(datas.get(0).getType().equals("전주 비빔밥")){
                setResult(200, intent);
            }else if(datas.get(0).getType().equals("콩나물국밥")){
                setResult(300, intent);
            }else if(datas.get(0).getType().equals("막걸리")){
                setResult(400, intent);
            }else if(datas.get(0).getType().equals("한옥마을 맛집")){
                setResult(500, intent);
            }
            finish();
        });

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
            intent.putExtra("type", data.getType());
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

    @Override
    public void isLikeChangeData(FoodListData foodListData) {
        for(int i=0; i<datas.size(); i++){
            if(datas.get(i).getStoreName().equals(foodListData.getStoreName())){
                datas.get(i).setLike(foodListData.isLike());
            }
        }

        adapter.notifyDataSetChanged();

        if(foodListData.isLike())
            Toast.makeText(getContext(), "즐겨찾기 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "즐겨찾기 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }

    FoodShareDialog shareDialog;
    @Override
    public void showDialog(FoodListData data) {
        shareDialog = new FoodShareDialog(this, data);
        shareDialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("datas", (Serializable) datas);
        if(datas.get(0).getType().equals("한정식")){
            setResult(100, intent);
        }else if(datas.get(0).getType().equals("전주 비빔밥")){
            setResult(200, intent);
        }else if(datas.get(0).getType().equals("콩나물국밥")){
            setResult(300, intent);
        }else if(datas.get(0).getType().equals("막걸리")){
            setResult(400, intent);
        }else if(datas.get(0).getType().equals("한옥마을 맛집")){
            setResult(500, intent);
        }
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
