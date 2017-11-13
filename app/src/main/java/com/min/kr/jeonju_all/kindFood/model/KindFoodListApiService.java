package com.min.kr.jeonju_all.kindFood.model;

import com.min.kr.jeonju_all.kindFood.data.KindFoodTotalData;
import com.min.kr.jeonju_all.util.RetrofitFactory;
import com.min.kr.jeonju_all.util.Variable;

import java.util.Map;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by minkr on 2017-10-25.
 */

public interface KindFoodListApiService {
    @GET(Variable._KINDFOOD_LIST_SERVER_REQUEST_URL)
    Maybe<KindFoodTotalData> getKindFoodList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static KindFoodListApiService create(){
            return  RetrofitFactory.initRetrofit().create(KindFoodListApiService.class);
        }
    }
}
