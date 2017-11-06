package com.example.minkr.jeonju_all.kindFood.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minkr.jeonju_all.R;
import com.example.minkr.jeonju_all.custom.CustomShareDialog;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;
import com.example.minkr.jeonju_all.kindFood.presenter.KindFoodPresenter;
import com.example.minkr.jeonju_all.util.Logger;

import java.util.List;

/**
 * Created by minkr on 2017-10-25.
 */

public class KindFoodAdapter extends RecyclerView.Adapter<KindFoodAdapter.ViewHolder> {

    Context mContext;
    List<KindFoodListData> datas;
    KindFoodPresenter mPresenter;
    boolean isLike = false;

    int[] menu_images = {R.drawable.img_fork, R.drawable.img_rib_soup, R.drawable.img_laundry, R.drawable.img_black_nodle, R.drawable.img_korean_food,
            R.drawable.img_nodle, R.drawable.img_siraegi, R.drawable.img_bibimbap, R.drawable.img_black_nodle, R.drawable.img_nodle};

//    int[] store_ids = {16953705, 16784799, 17115770, 34005058, 16986870, 11710472, 19555701, 16985833, 36004947, 36004175};

    public KindFoodAdapter(Context mContext, List<KindFoodListData> datas, KindFoodPresenter presenter){
        this.mContext = mContext;
        this.datas = datas;
        this.mPresenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_kind_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KindFoodListData data = datas.get(position);
//        data.setStoreId(store_ids[position]+"");
        //Logger.log("#16 data -> "+datas);
        holder.iv_food.setBackgroundResource(menu_images[data.getId()-1]);
        holder.tv_shop_name.setText(data.getName());
        holder.tv_address.setText(data.getAddress());
        holder.tv_tel.setText(data.getTel());
        holder.tv_menu.setText(data.getFoodName());
        holder.tv_price.setText(data.getPrice());

        holder.tv_tel.setOnClickListener(v->{
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:"+data.getTel()));
            mContext.startActivity(call);
        });

        holder.getView().setOnClickListener(v->{
            Logger.log("#12 data->"+ data.toString());
            mPresenter.getFoodStoreInfo(data);
        });

        holder.ib_like.setOnClickListener(v->{
            if(isLike)
                holder.ib_like.setImageResource(R.drawable.ic_like_n);
            else
                holder.ib_like.setImageResource(R.drawable.ic_like_p);

            isLike = !isLike;
        });
        holder.ib_share.setOnClickListener(v->{
            mPresenter.showDialog(data);
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_food;
        TextView tv_shop_name;
        TextView tv_address;
        TextView tv_tel;
        TextView tv_menu;
        TextView tv_price;
        View view;
        ImageButton ib_like;
        ImageButton ib_share;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            iv_food = (ImageView) itemView.findViewById(R.id.iv_food);
            tv_shop_name = (TextView) itemView.findViewById(R.id.tv_shop_name);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
            tv_tel = (TextView) itemView.findViewById(R.id.tv_tel);
            tv_menu = (TextView) itemView.findViewById(R.id.tv_menu);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            ib_like = (ImageButton) itemView.findViewById(R.id.ib_like);
            ib_share = (ImageButton) itemView.findViewById(R.id.ib_share);
        }

        public View getView(){
            return view;
        }
    }
}
