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

public interface FoodWineListApiService {
    @GET(Variable._FOOD_WINE_LIST_SERVER_REQUEST_URL)
    Maybe<FoodTotalData> getFoodWineList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static FoodWineListApiService create(){
            return  RetrofitFactory.initRetrofit().create(FoodWineListApiService.class);
        }
    }
}
