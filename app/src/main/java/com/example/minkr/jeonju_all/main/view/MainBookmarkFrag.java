package com.example.minkr.jeonju_all.main.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.main.data.BookmarkList;
import com.example.minkr.jeonju_all.main.presenter.MainPresenter;
import com.example.minkr.jeonju_all.util.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minkr on 2017-10-24.
 */

public class MainBookmarkFrag extends Fragment implements MainView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.btn_all_delete)
    Button btn_all_delete;

    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    LinearLayoutManager mLayoutManager;
    MainPresenter presenter;
    MainBookmarkAdapter adapter;
    ArrayList<BookmarkList> datas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = new MainPresenter();
        presenter.attachView(this);

        progressBar.setVisibility(View.VISIBLE);
        presenter.getBookmarkList();

        init();
        setListener();
    }

    private void init() {

        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MainBookmarkAdapter(this, datas, presenter);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
    private void setListener() {
        btn_all_delete.setOnClickListener(v->{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder
                    .setMessage("모두 삭제하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("아니오",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("네",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    presenter.allDeleteData();
                                }
                            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        });
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
        Logger.log("#223 onDestroy");
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        datas.clear();
//    }

    @Override
    public void notConnectNetworking() {

    }

    @Override
    public void getBookmarkList(List<BookmarkList> bookmarkLists) {
        datas.clear();
        datas.addAll(bookmarkLists);
        adapter.notifyDataSetChanged();

        if(datas.size()==0){
            ll_empty.setVisibility(View.VISIBLE);
            btn_all_delete.setVisibility(View.GONE);
        }

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showStoreInfo(BookmarkList data) {
        Intent intent = new Intent(getContext(), BookmarkDetailActivity.class);
        intent.putExtra("data",data.getHomepage_url());
        intent.putExtra("type", data.getType());
        startActivity(intent);
    }

    @Override
    public void allDeleteData() {
        datas.clear();
        adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
        btn_all_delete.setVisibility(View.GONE);
        ll_empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDeleteDialog(BookmarkList data, int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder
                .setMessage("삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("네",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.deleteData(data,position);
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void deleteData(BookmarkList bookmarkList, int position) {
        Logger.log("#47 delete position ->"+position+",datas size->"+datas.size()+", datas->"+datas.toString());
        datas.remove(bookmarkList);
        adapter.notifyItemRemoved(position);

        if(datas.size() == 0) {
            btn_all_delete.setVisibility(View.GONE);
            ll_empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getAddressClick(BookmarkList data) {
        Intent intent = new Intent(getContext(), BookmarkMapActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}