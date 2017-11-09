package com.example.minkr.jeonju_all.facility.view;

import com.example.minkr.jeonju_all.common.view.MvpView;
import com.example.minkr.jeonju_all.facility.data.ParkListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-09.
 */

public interface ParkView extends MvpView {
    void getParkList(List<ParkListData> parkListData);
}
