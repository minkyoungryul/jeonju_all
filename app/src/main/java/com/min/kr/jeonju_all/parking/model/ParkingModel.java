package com.min.kr.jeonju_all.parking.model;

import android.content.Context;

import com.min.kr.jeonju_all.parking.data.ParkingTotalData;
import com.min.kr.jeonju_all.util.Variable;

import java.util.HashMap;

import io.reactivex.Maybe;

/**
 * Created by minkr on 2017-10-22.
 */

public class ParkingModel {

    Context mContext;

    public ParkingModel(Context mContext){
        this.mContext = mContext;
    }

    public Maybe<ParkingTotalData> getParkingList(){
        HashMap<String, String> param = new HashMap<>();
        param.put("authApiKey", Variable._PARKING_SERVICE_KEY);

        return ParkingListApiService.Factory.create().getBusStopList(param);
    }
}
