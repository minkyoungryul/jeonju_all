package com.example.minkr.jeonju_all.food.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.food.view.FoodDetailView;

/**
 * Created by minkr on 2017-11-03.
 */

public class FoodDetailPresenter implements Presenter<FoodDetailView> {

    FoodDetailView view;
    Context mContext;

    @Override
    public void attachView(FoodDetailView view) {
        this.view = view;
        mContext = view.getContext();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void notConnectNetworking() {
        view.notConnectNetworking();
    }

    public void getStoreInfo(FoodListData data) {
        view.getStoreInfo(data);
    }

    public void getAddressClick(FoodListData data) {
        view.getAddressClick(data);
    }

}
