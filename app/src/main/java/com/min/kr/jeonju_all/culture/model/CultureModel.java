package com.min.kr.jeonju_all.culture.model;

import android.content.Context;

import com.min.kr.jeonju_all.culture.data.CultureTotalData;
import com.min.kr.jeonju_all.util.Variable;

import java.util.HashMap;

import io.reactivex.Maybe;

/**
 * Created by minkr on 2017-11-07.
 */

public class CultureModel {
    Context mContext;
    public CultureModel(Context mContext) {
        this.mContext = mContext;
    }


    public Maybe<CultureTotalData> getCultureList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return CultureListApiService.Factory.create().getCultureList(hashMap);
    }
}
