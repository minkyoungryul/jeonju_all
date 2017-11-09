package com.example.minkr.jeonju_all.facility.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.facility.data.FacilityListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-08.
 */

public interface PoliceView extends MvpView {
    void getPoliceList(List<FacilityListData> facilityListData);
}
