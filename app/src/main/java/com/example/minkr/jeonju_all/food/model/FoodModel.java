package com.example.minkr.jeonju_all.food.model;

import android.content.Context;

import com.example.minkr.jeonju_all.food.data.FoodTotalData;
import com.example.minkr.jeonju_all.util.Variable;

import java.util.HashMap;

import io.reactivex.Maybe;
import io.reactivex.disposables.Disposable;

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
}
