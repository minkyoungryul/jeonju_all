package com.min.kr.jeonju_all.culture.view;

import com.min.kr.jeonju_all.common.view.MvpView;
import com.min.kr.jeonju_all.culture.data.CultureListData;
import com.min.kr.jeonju_all.main.data.BookmarkList;

import java.util.List;

/**
 * Created by minkr on 2017-11-07.
 */

public interface CultureView extends MvpView {
    void getCultureList(List<CultureListData> cultureListData);
    void showInfo(CultureListData data);
    void getAddressClick(CultureListData data);

    void getCultureDBData(List<BookmarkList> bookmarkLists);

    void isLikeChangeData(CultureListData cultureListData);

    void showDialog(CultureListData data);
}
