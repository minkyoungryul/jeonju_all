package com.example.minkr.jeonju_all.culture.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.culture.data.CultureListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-07.
 */

public interface CultureView extends MvpView {
    void getCultureList(List<CultureListData> cultureListData);
    void showInfo(CultureListData data);
}