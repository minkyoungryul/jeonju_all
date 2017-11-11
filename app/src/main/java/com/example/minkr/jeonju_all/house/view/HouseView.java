package com.example.minkr.jeonju_all.house.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.house.data.HouseListData;
import com.example.minkr.jeonju_all.main.data.BookmarkList;

import java.util.List;

/**
 * Created by minkr on 2017-11-06.
 */

public interface HouseView extends MvpView{
    void getHouseList(List<HouseListData> houseListData);

    void getStoreInfo(HouseListData data);

    void getAddressClick(HouseListData data);

    void getHouseDBData(List<BookmarkList> bookmarkLists);

    void isLikeChangeData(HouseListData houseListData);
}
