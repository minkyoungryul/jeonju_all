package com.example.minkr.jeonju_all.food.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.main.data.BookmarkList;

import java.util.List;

/**
 * Created by minkr on 2017-11-03.
 */

public interface FoodView extends MvpView{
    void getFoodRiceDatas(List<FoodListData> foodListData);

    void getFoodBibimbapDatas(List<FoodListData> foodListData);

    void getFoodKongbapDatas(List<FoodListData> foodListData);

    void getFoodWineDatas(List<FoodListData> foodListData);

    void getFoodHanokDatas(List<FoodListData> foodListData);

    void getStoreInfo(FoodListData data);

    void getFoodDBData(List<BookmarkList> bookmarkLists);

    void isLikeChangeData(FoodListData foodListData);

    void getAddressClick(FoodListData data);

    void showDialog(FoodListData data);
}
