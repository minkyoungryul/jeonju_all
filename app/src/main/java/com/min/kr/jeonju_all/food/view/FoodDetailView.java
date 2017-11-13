package com.min.kr.jeonju_all.food.view;

import com.min.kr.jeonju_all.common.view.MvpView;
import com.min.kr.jeonju_all.food.data.FoodListData;

/**
 * Created by minkr on 2017-11-03.
 */

public interface FoodDetailView extends MvpView {
    void getStoreInfo(FoodListData data);
    void getAddressClick(FoodListData data);

    void isLikeChangeData(FoodListData foodListData);

    void showDialog(FoodListData data);
}
