package com.example.minkr.jeonju_all.house.model;

import com.example.minkr.jeonju_all.food.model.FoodKongbapListApiService;
import com.example.minkr.jeonju_all.house.data.HouseTotalData;
import com.example.minkr.jeonju_all.util.RetrofitFactory;
import com.example.minkr.jeonju_all.util.Variable;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by minkr on 2017-11-06.
 */

public interface HouseListApiService {
    @GET(Variable._HOUSE_LIST_SERVER_REQUEST_URL)
    Maybe<HouseTotalData> getHouseList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static HouseListApiService create(){
            return  RetrofitFactory.initRetrofit().create(HouseListApiService.class);
        }
    }
}
