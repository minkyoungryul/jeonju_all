package com.example.minkr.jeonju_all.culture.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.culture.data.CultureListData;
import com.example.minkr.jeonju_all.culture.presenter.CulturePresenter;
import com.example.minkr.jeonju_all.util.Logger;

import java.util.List;

/**
 * Created by minkr on 2017-11-07.
 */

public class CultureAdapter extends RecyclerView.Adapter<CultureAdapter.ViewHolder> {

    Context mContext;
    List<CultureListData> datas;
    CulturePresenter presenter;

    public CultureAdapter(Context mContext, List<CultureListData> datas, CulturePresenter presenter) {
        this.mContext = mContext;
        this.datas = datas;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_culture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CultureListData data = datas.get(position);
        Logger.log("#20 data->"+data.toString());

        if(data.getTel() == null || data.getTel().equals(" ")){
            holder.tv_tel.setVisibility(View.GONE);
            holder.iv_call.setVisibility(View.GONE);
        }else{
            holder.tv_tel.setText(data.getTel());
        }

        holder.tv_store_name.setText(data.getTitle());
        holder.tv_address.setText(data.getAddr()+" "+data.getAddrDtl());

        if (data.getDataContent() == null || data.getDataContent().equals(" ")){
            holder.tv_dot.setVisibility(View.GONE);
            holder.tv_content.setVisibility(View.GONE);
        }else{
            holder.tv_content.setText(data.getDataContent());
        }

        Glide.with(mContext)
                .load(data.getImg_url())
                .fitCenter()
                .into(holder.iv_house);

        holder.tv_tel.setOnClickListener(v->{
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:"+data.getTel()));
            mContext.startActivity(call);
        });

        holder.getView().setOnClickListener(v->{
            presenter.showInfo(data);
        });

        holder.tv_address.setOnClickListener(v -> {
            presenter.getAddressClick(data);
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
        View view;
        TextView tv_tel;
        TextView tv_dot;
        ImageView iv_call;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_house = (ImageView) itemView.findViewById(R.id.iv_house);
            tv_store_name = (TextView) itemView.findViewById(R.id.tv_store_name);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_tel = (TextView) itemView.findViewById(R.id.tv_tel);
            tv_dot = (TextView) itemView.findViewById(R.id.tv_dot);
            iv_call = (ImageView) itemView.findViewById(R.id.iv_call);
            this.view = itemView;
        }

        public View getView(){
            return view;
        }
    }
}
