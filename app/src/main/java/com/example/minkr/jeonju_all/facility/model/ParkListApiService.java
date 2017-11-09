package com.example.minkr.jeonju_all.facility.model;

import com.example.minkr.jeonju_all.facility.data.ParkTotalData;
import com.example.minkr.jeonju_all.util.RetrofitFactory;
import com.example.minkr.jeonju_all.util.Variable;

import java.util.Map;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by minkr on 2017-11-08.
 */

public interface ParkListApiService {
    @GET(Variable._PARK_LIST_SERVER_REQUEST_URL)
    Maybe<ParkTotalData> getParkList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static ParkListApiService create(){
            return  RetrofitFactory.initRetrofit().create(ParkListApiService.class);
        }
    }
}
