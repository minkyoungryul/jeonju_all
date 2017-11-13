package com.min.kr.jeonju_all.main.view;

import com.min.kr.jeonju_all.common.view.MvpView;
import com.min.kr.jeonju_all.main.data.BookmarkList;

import java.util.List;

/**
 * Created by minkr on 2017-10-25.
 */

public interface MainView extends MvpView{
    void getBookmarkList(List<BookmarkList> bookmarkLists);

    void showStoreInfo(BookmarkList data);

    void allDeleteData();

    void showDeleteDialog(BookmarkList data, int position);

    void deleteData(BookmarkList bookmarkList, int position);

    void getAddressClick(BookmarkList data);

    void showDialog(BookmarkList data);
}