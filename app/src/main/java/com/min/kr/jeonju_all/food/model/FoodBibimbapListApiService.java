package com.min.kr.jeonju_all.food.model;

import com.min.kr.jeonju_all.food.data.FoodTotalData;
import com.min.kr.jeonju_all.util.RetrofitFactory;
import com.min.kr.jeonju_all.util.Variable;

import java.util.Map;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by minkr on 2017-11-03.
 */

public interface FoodBibimbapListApiService {
    @GET(Variable._FOOD_BIBIMBAP_LIST_SERVER_REQUEST_URL)
    Maybe<FoodTotalData> getFoodBibimbapList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static FoodBibimbapListApiService create(){
            return  RetrofitFactory.initRetrofit().create(FoodBibimbapListApiService.class);
        }
    }
}
