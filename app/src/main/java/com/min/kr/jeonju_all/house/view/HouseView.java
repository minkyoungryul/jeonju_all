package com.min.kr.jeonju_all.house.view;

import com.min.kr.jeonju_all.common.view.MvpView;
import com.min.kr.jeonju_all.house.data.HouseListData;
import com.min.kr.jeonju_all.main.data.BookmarkList;

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

    void showDialog(HouseListData data);
}
