package com.min.kr.jeonju_all.food.model;

import android.content.Context;

import com.min.kr.jeonju_all.food.data.FoodTotalData;
import com.min.kr.jeonju_all.util.Variable;

import java.util.HashMap;

import io.reactivex.Maybe;

/**
 * Created by minkr on 2017-11-03.
 */

public class FoodModel {

    Context mContext;

    public FoodModel(Context mContext){
        this.mContext = mContext;
    }


    public Maybe<FoodTotalData> getFoodRiceList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._PARKING_SERVICE_KEY);

        return FoodRiceListApiService.Factory.create().getFoodRiceList(hashMap);
    }

    public Maybe<FoodTotalData> getFoodBibimbapList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._PARKING_SERVICE_KEY);

        return FoodBibimbapListApiService.Factory.create().getFoodBibimbapList(hashMap);
    }

    public Maybe<FoodTotalData> getFoodKongbapList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._PARKING_SERVICE_KEY);

        return FoodKongbapListApiService.Factory.create().getFoodKongbapList(hashMap);
    }

    public Maybe<FoodTotalData> getFoodWineList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._PARKING_SERVICE_KEY);

        return FoodWineListApiService.Factory.create().getFoodWineList(hashMap);
    }

    public Maybe<FoodTotalData> getFoodHanokList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._PARKING_SERVICE_KEY);

        return FoodHanokListApiService.Factory.create().getFoodHanokList(hashMap);
    }
}
