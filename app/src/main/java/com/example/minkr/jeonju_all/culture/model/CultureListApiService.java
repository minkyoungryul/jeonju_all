package com.example.minkr.jeonju_all.culture.model;

import com.example.minkr.jeonju_all.culture.data.CultureTotalData;
import com.example.minkr.jeonju_all.food.data.FoodTotalData;
import com.example.minkr.jeonju_all.food.model.FoodBibimbapListApiService;
import com.example.minkr.jeonju_all.util.RetrofitFactory;
import com.example.minkr.jeonju_all.util.Variable;

import java.util.Map;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by minkr on 2017-11-07.
 */

public interface CultureListApiService {
    @GET(Variable._CULTURE_LIST_SERVER_REQUEST_URL)
    Maybe<CultureTotalData> getCultureList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static CultureListApiService create(){
            return  RetrofitFactory.initRetrofit().create(CultureListApiService.class);
        }
    }}
