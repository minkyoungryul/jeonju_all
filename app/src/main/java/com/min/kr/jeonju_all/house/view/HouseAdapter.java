package com.min.kr.jeonju_all.house.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.min.kr.jeonju_all.R;
import com.min.kr.jeonju_all.house.data.HouseListData;
import com.min.kr.jeonju_all.house.presenter.HousePresenter;

import java.util.List;

/**
 * Created by minkr on 2017-11-06.
 */

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {

    Context mContext;
    List<HouseListData> datas;
    HousePresenter presenter;

    public HouseAdapter(Context mContext, List<HouseListData> datas, HousePresenter presenter) {
        this.mContext = mContext;
        this.datas = datas;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_house, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HouseListData data = datas.get(position);

        if(data.isLike()){
            holder.ib_like.setImageResource(R.drawable.ic_like_p);
        }else{
            holder.ib_like.setImageResource(R.drawable.ic_like_n);
        }
        Glide.with(mContext)
                .load(data.getImg_url())
                .fitCenter()
                .into(holder.iv_house);

        holder.tv_store_name.setText(data.getStoreName());
        holder.tv_address.setText(data.getAddress());
        holder.tv_content.setText(data.getContent());

        holder.getView().setOnClickListener(v->{
            presenter.getStoreInfo(data);
        });

        holder.tv_address.setOnClickListener(v -> {
            presenter.getAddressClick(data);
        });

        holder.ib_share.setOnClickListener(v -> {
            presenter.showDialog(data);
        });

        holder.ib_like.setOnClickListener(v->{
            if(data.isLike()){
                presenter.deleteDBData(data);
            }else{
                presenter.insertDBData(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_house;
        TextView tv_store_name;
        TextView tv_address;
        TextView tv_content;
        ImageButton ib_like;
        ImageButton ib_share;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_house = (ImageView) itemView.findViewById(R.id.iv_house);
            tv_store_name = (TextView) itemView.findViewById(R.id.tv_store_name);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_share = (ImageButton) itemView.findViewById(R.id.ib_share);
            this.view = itemView;
        }

        public View getView(){
            return view;
        }
    }
}
