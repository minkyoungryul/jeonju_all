package com.min.kr.jeonju_all.parking.view;

import com.min.kr.jeonju_all.common.view.MvpView;
import com.min.kr.jeonju_all.parking.data.ParkingListData;

import java.util.List;

/**
 * Created by minkr on 2017-10-22.
 */

public interface ParkingVIew extends MvpView{
    void getParkingList(List<ParkingListData> parkingListData);
}
