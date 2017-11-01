package com.example.minkr.jeonju_all.parking.model;

import com.example.minkr.jeonju_all.parking.data.ParkingTotalData;
import com.example.minkr.jeonju_all.util.RetrofitFactory;
import com.example.minkr.jeonju_all.util.Variable;

import java.util.Map;

import io.reactivex.Maybe;
import retrofit2.Converter;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by minkr on 2017-10-22.
 */

public interface ParkingListApiService {
    @GET(Variable._PARKING_LIST_SERVER_REQUEST_URL)
    Maybe<ParkingTotalData> getBusStopList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static ParkingListApiService create(){
            return  RetrofitFactory.initRetrofit().create(ParkingListApiService.class);
        }
    }
}
