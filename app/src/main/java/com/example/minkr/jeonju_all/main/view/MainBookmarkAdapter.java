package com.example.minkr.jeonju_all.main.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.main.data.BookmarkList;
import com.example.minkr.jeonju_all.main.presenter.MainPresenter;

import java.util.ArrayList;

/**
 * Created by minkr on 2017-11-10.
 */

public class MainBookmarkAdapter extends RecyclerView.Adapter<MainBookmarkAdapter.ViewHolder>{

    MainBookmarkFrag mainBookmarkFrag;
    ArrayList<BookmarkList> datas;
    MainPresenter presenter;

    public MainBookmarkAdapter(MainBookmarkFrag mainBookmarkFrag, ArrayList<BookmarkList> datas, MainPresenter presenter) {
        this.mainBookmarkFrag = mainBookmarkFrag;
        this.datas = datas;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bookmark, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookmarkList data = datas.get(position);

        Glide.with(mainBookmarkFrag.getContext())
                .load(data.getImg_url())
                .fitCenter()
                .into(holder.iv_food);

        holder.tv_store_name.setText(data.getTitle());

        holder.tv_tel.setText(data.getTel());

        if(data.getAddress() == null || data.getAddress().equals(" ") || data.getAddress().equals("")){
            holder.tv_address.setVisibility(View.GONE);
            holder.iv_map.setVisibility(View.GONE);
        }else{
            holder.tv_address.setText(data.getAddress());
        }

        if(data.getTel() == null || data.getTel().equals("--") || data.getTel().equals(" ") || data.getTel().equals("")){
            holder.tv_tel.setVisibility(View.GONE);
            holder.iv_call.setVisibility(View.GONE);
        }else{
            holder.tv_tel.setText(data.getTel());
        }

        holder.getView().setOnClickListener(v->{
            presenter.showStoreInfo(data);
        });

        holder.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                presenter.showDeleteDialog(data,position);
                return true;
            }
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
        ImageView iv_food;
        TextView tv_store_name;
        TextView tv_address;
        View itemView;
        ImageButton ib_like;
        TextView tv_tel;
        ImageView iv_map;
        ImageView iv_call;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_food = (ImageView) itemView.findViewById(R.id.iv_food);
            tv_store_name = (TextView) itemView.findViewById(R.id.tv_store_name);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            tv_tel = (TextView) itemView.findViewById(R.id.tv_tel);
            iv_map = (ImageView) itemView.findViewById(R.id.iv_map);
            iv_call = (ImageView) itemView.findViewById(R.id.iv_call);
            this.itemView = itemView;
        }

        public View getView(){
            return itemView;
        }
    }
}
