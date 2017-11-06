package com.example.minkr.jeonju_all.house.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.house.data.HouseListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-06.
 */

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {

    Context mContext;
    List<HouseListData> datas;

    public HouseAdapter(Context mContext, List<HouseListData> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_house, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
