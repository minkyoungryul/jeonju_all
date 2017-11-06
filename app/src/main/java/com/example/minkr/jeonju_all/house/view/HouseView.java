package com.example.minkr.jeonju_all.house.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.house.data.HouseListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-06.
 */

public interface HouseView extends MvpView{
    void getHouseList(List<HouseListData> houseListData);
}
