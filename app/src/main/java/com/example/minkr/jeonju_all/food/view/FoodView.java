package com.example.minkr.jeonju_all.food.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.food.data.FoodListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-03.
 */

public interface FoodView extends MvpView{
    void getFoodRiceDatas(List<FoodListData> foodListData);
}
