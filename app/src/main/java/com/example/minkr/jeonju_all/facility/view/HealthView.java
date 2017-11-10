package com.example.minkr.jeonju_all.facility.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.facility.data.HealthListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-09.
 */

public interface HealthView extends MvpView {
    void getHealthList(List<HealthListData> healthListData);
}
