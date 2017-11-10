package com.example.minkr.jeonju_all.main.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.main.BookmarkList;

import java.util.List;

/**
 * Created by minkr on 2017-10-25.
 */

public interface MainView extends MvpView{
    void getBookmarkList(List<BookmarkList> bookmarkLists);

    void showStoreInfo(BookmarkList data);

    void allDeleteData();
}