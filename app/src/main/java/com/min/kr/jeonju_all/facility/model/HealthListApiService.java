package com.min.kr.jeonju_all.facility.model;

import com.min.kr.jeonju_all.facility.data.HealthTotalData;
import com.min.kr.jeonju_all.util.RetrofitFactory;
import com.min.kr.jeonju_all.util.Variable;

import java.util.Map;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by minkr on 2017-11-08.
 */

public interface HealthListApiService {
    @GET(Variable._HEALTH_LIST_SERVER_REQUEST_URL)
    Maybe<HealthTotalData> getHealthList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static HealthListApiService create(){
            return  RetrofitFactory.initRetrofit().create(HealthListApiService.class);
        }
    }
}
