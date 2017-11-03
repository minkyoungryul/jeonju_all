package com.example.minkr.jeonju_all.food.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.food.data.FoodListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-03.
 */

public class FoodWineAdapter extends RecyclerView.Adapter<FoodWineAdapter.ViewHolder> {

    Context mContext;
    List<FoodListData> datas;
    boolean isLike = false;

    public FoodWineAdapter(Context mContext, List<FoodListData> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_food_rice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodListData data = datas.get(position);
        Glide.with(mContext)
                .load(data.getMainImg())
                .fitCenter()
                .into(holder.iv_food);

        holder.tv_store_name.setText(data.getStoreName());
        holder.tv_address.setText(data.getNewAddr());
        holder.tv_main_menu.setText(data.getMainMenu());


        holder.ib_like.setOnClickListener(v->{
            if(isLike)
                holder.ib_like.setImageResource(R.drawable.ic_like_n);
            else
                holder.ib_like.setImageResource(R.drawable.ic_like_p);

            isLike = !isLike;
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_food;
        TextView tv_store_name;
        TextView tv_main_menu;
        TextView tv_address;
        View itemView;
        ImageButton ib_like;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_food = (ImageView) itemView.findViewById(R.id.iv_food);
            tv_store_name = (TextView) itemView.findViewById(R.id.tv_store_name);
            tv_main_menu = (TextView) itemView.findViewById(R.id.tv_main_menu);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            this.itemView = itemView;
        }

        public View getView(){
            return itemView;
        }
    }
}
