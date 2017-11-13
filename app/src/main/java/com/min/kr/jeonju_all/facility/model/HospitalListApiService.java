package com.min.kr.jeonju_all.facility.model;

import com.min.kr.jeonju_all.facility.data.HospitalTotalData;
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

public interface HospitalListApiService{
    @GET(Variable._HOSPITAL_ALL_LIST_SERVER_REQUEST_URL)
    Maybe<HospitalTotalData> getHospitAllList(@QueryMap Map<String, String> map);

    @GET(Variable._HOSPITAL_INTERNAL_LIST_SERVER_REQUEST_URL)
    Maybe<HospitalTotalData> getHospitInternalList(@QueryMap Map<String, String> map);

    @GET(Variable._HOSPITAL_KOREA_LIST_SERVER_REQUEST_URL)
    Maybe<HospitalTotalData> getHospitKoreaList(@QueryMap Map<String, String> map);

    @GET(Variable._HOSPITAL_POSTPARTUM_LIST_SERVER_REQUEST_URL)
    Maybe<HospitalTotalData> getHospitPostList(@QueryMap Map<String, String> map);

    @GET(Variable._HOSPITAL_DENTIST_LIST_SERVER_REQUEST_URL)
    Maybe<HospitalTotalData> getHospitDentistList(@QueryMap Map<String, String> map);

    @GET(Variable._MEDICINE_LIST_SERVER_REQUEST_URL)
    Maybe<HospitalTotalData> getMedicineList(@QueryMap Map<String, String> map);

    class Factory extends Converter.Factory{
        public static HospitalListApiService create(){
            return  RetrofitFactory.initRetrofit().create(HospitalListApiService.class);
        }
    }
}
