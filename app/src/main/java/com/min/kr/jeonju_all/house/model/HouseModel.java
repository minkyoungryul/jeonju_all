package com.min.kr.jeonju_all.house.model;

import android.content.Context;

import com.min.kr.jeonju_all.house.data.HouseTotalData;
import com.min.kr.jeonju_all.util.Variable;

import java.util.HashMap;

import io.reactivex.Maybe;

/**
 * Created by minkr on 2017-11-06.
 */

public class HouseModel {

    Context mContext;
    public HouseModel(Context mContext) {
        this.mContext = mContext;
    }

    public Maybe<HouseTotalData> getHouseList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._PARKING_SERVICE_KEY);

        return HouseListApiService.Factory.create().getHouseList(hashMap);
    }
}
