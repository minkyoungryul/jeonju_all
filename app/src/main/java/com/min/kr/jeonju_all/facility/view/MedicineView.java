package com.min.kr.jeonju_all.facility.view;

import com.min.kr.jeonju_all.common.view.MvpView;
import com.min.kr.jeonju_all.facility.data.HospitalListData;

import java.util.List;

/**
 * Created by minkr on 2017-11-09.
 */

public interface MedicineView extends MvpView{
    void getMedicineList(List<HospitalListData> hospitalListData);
}
