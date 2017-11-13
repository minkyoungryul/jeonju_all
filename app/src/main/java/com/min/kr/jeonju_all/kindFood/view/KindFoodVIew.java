package com.min.kr.jeonju_all.kindFood.view;

import com.min.kr.jeonju_all.common.view.MvpView;
import com.min.kr.jeonju_all.kindFood.data.KindFoodListData;
import com.min.kr.jeonju_all.main.data.BookmarkList;

import java.util.List;

/**
 * Created by minkr on 2017-10-22.
 */

public interface KindFoodVIew extends MvpView {
    void getKindFoodDatas(List<KindFoodListData> kindFoodListDatas);

    void getFoodStoreInfo(KindFoodListData data);

    void showDialog(KindFoodListData data);

    void showMap(KindFoodListData data);

    void getKindDBData(List<BookmarkList> bookmarkLists);

    void isLikeChangeData(KindFoodListData kindFoodListData);
}
