package com.example.minkr.jeonju_all.food.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.food.data.FoodListData;

/**
 * Created by minkr on 2017-11-03.
 */

public interface FoodDetailView extends MvpView {
    void getStoreInfo(FoodListData data);
    void getAddressClick(FoodListData data);
}
