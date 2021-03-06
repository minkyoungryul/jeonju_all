package com.min.kr.jeonju_all.kindFood.model;

import android.content.Context;

import com.min.kr.jeonju_all.kindFood.data.KindFoodTotalData;
import com.min.kr.jeonju_all.util.Variable;

import java.util.HashMap;

import io.reactivex.Maybe;

/**
 * Created by minkr on 2017-10-22.
 */

public class KindFoodModel {
    Context mContext;

    public KindFoodModel(Context context){
        mContext = context;
    }

    public Maybe<KindFoodTotalData> getKindFoodList(){
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("ServiceKey", Variable._KINDFOOD_SERVICE_KEY);

        return KindFoodListApiService.Factory.create().getKindFoodList(hashMap);
    }
}
