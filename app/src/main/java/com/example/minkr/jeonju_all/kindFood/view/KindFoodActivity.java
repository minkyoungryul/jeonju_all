package com.example.minkr.jeonju_all.kindFood.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.minkr.jeonju_all.custom.CustomShareDialog;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;
import com.example.minkr.jeonju_all.kindFood.presenter.KindFoodPresenter;
import com.example.minkr.jeonju_all.main.BookmarkList;
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
    List<BookmarkList> bookmarkLists = new ArrayList<>();

    //KindFoodMapActivity kindFoodMapActivity;


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
        //kindFoodMapActivity = (KindFoodMapActivity)getContext();
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
        String[] img_url =
                {"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20150727_135%2Fmaxddong00_14379825629150FDh0_PNG%2F%25B8%25B8%25B3%25AA%25BA%25B0%25B9%25CC_%25B5%25B7%25B0%25A1%25BD%25BA_%25BB%25E7%25B6%25FB%25B5%25B5_%25C0%25CC%25B7%25B1_%25BB%25E7%25B6%25FB%25C0%25CC_%25BE%25F8%25B3%25D7%25BF%25E4_%25A4%25BE9.png&type=f420_312&quality=95&autoRotate=true",
                        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles2.naver.net%2F20141120_145%2Fjeonjutacu_1416460775633Afq3k_JPEG%2Fq45145.jpg&type=f420_312&quality=95&autoRotate=true",
                        "http://kwaus.org/files/attach/images/450/928/b68c0bb72e158bf6400987e23e363b13.jpg",
                        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20160714_29%2Fwltn111_14684648551484tPEw_JPEG%2FKakaoTalk_20160714_110524304.jpg%234128x3096&type=f420_312&quality=95&autoRotate=true",
                        "http://blogthumb2.naver.net/20150901_66/pgs0853_1441056256999faFX4_JPEG/%C0%FC%C1%D6%B9%E4%C1%FD_%B1%E2%B8%B0%B7%CE%B9%E9%B9%DD6.JPG?type=w2",
                        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20111207_130%2Fksijc_1323199117237jI0Si_JPEG%2FSDC13213.JPG%23650x487&type=f420_312&quality=95&autoRotate=true",
                        "http://post.phinf.naver.net/MjAxNzAzMTRfMTkx/MDAxNDg5NDg3MTEyMTIw.x45ANvVL2s_wgB4-Cym5c1C41ISpS695AEmwBqvrgSIg.9_QTNWLJDoXjszmduvcTS68wA9tZapeVS5LcGQkeZ9og.JPEG/%EC%A0%84%EC%A3%BC%EB%A7%9B%EC%A7%91_%EC%8B%A0%EC%8B%9C%EA%B0%80%EC%A7%80_%EA%B3%A0%ED%96%A5%EC%8B%9C%EB%9E%98%EA%B8%B0_%2821%29.jpg?type=w1200",
                        "http://cfile30.uf.tistory.com/image/124BB6344C91F6C0372F43",
                        "http://www.dtnews24.com/data/photos/img_old/201407/364940_201407031023432_0.jpg",
                        "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20141127_291%2Fchicntee_1417077886135f1R43_JPEG%2FIMG_3443.JPG&type=f420_312&quality=95&autoRotate=true"
        };
        for(int i=0; i<kindFoodListDatas.size(); i++){
            kindFoodListDatas.get(i).setStoreId(store_ids[i]+"");
            kindFoodListDatas.get(i).setImg_url(img_url[i]);
        }

        kindFoodList.addAll(kindFoodListDatas);
        Logger.log("#6 kindFoodList ->"+ kindFoodList.toString());
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        presenter.getKindDBData();
    }

    @Override
    public void getFoodStoreInfo(KindFoodListData data) {
        Intent intent = new Intent(KindFoodActivity.this, FoodStoreInfoActivity.class);
        intent.putExtra("storeId", data.getStoreId());
        intent.putExtra("datas",0);
        startActivity(intent);
    }

    CustomShareDialog shareDialog;
    @Override
    public void showDialog(KindFoodListData data) {
        shareDialog = new CustomShareDialog(this,data);
        shareDialog.show();
    }

    @Override
    public void showMap(KindFoodListData data){
        Intent intent = new Intent(KindFoodActivity.this, KindFoodMap2Activity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void getKindDBData(List<BookmarkList> bookmarkLists) {
        Logger.log("#16 bookmarkLists->"+bookmarkLists.toString() + ", kindFoodList->" + kindFoodList.toString());
        for (int i=0; i<kindFoodList.size(); i++){
            for(int j=0; j<bookmarkLists.size(); j++){
                if(bookmarkLists.get(j).getTitle().equals(kindFoodList.get(i).getName())){
                    Logger.log("#17 data->"+kindFoodList.toString());
                    kindFoodList.get(i).setLike(true);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void isLikeChangeData(KindFoodListData kindFoodListData) {
        for(int i=0; i<kindFoodList.size(); i++){
            if(kindFoodList.get(i).getName().equals(kindFoodListData.getName())){
                kindFoodList.get(i).setLike(kindFoodListData.isLike());
                Logger.log("#19 change kindFoodList->"+kindFoodList.toString());
            }
        }
        adapter.notifyDataSetChanged();

        if(kindFoodListData.isLike())
            Toast.makeText(getContext(), "즐겨찾기 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), "즐겨찾기 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
