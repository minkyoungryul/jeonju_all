package com.example.minkr.jeonju_all.food.model;

import com.example.minkr.jeonju_all.food.data.FoodTotalData;
import com.example.minkr.jeonju_all.util.RetrofitFactory;
import com.example.minkr.jeonju_all.util.Variable;

import java.util.Map;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by minkr on 2017-11-03.
 */

public interface FoodRiceListApiService {
    @GET(Variable._FOOD_RICE_LIST_SERVER_REQUEST_URL)
    Maybe<FoodTotalData> getFoodRiceList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static FoodRiceListApiService create(){
            return  RetrofitFactory.initRetrofit().create(FoodRiceListApiService.class);
        }
    }
}
