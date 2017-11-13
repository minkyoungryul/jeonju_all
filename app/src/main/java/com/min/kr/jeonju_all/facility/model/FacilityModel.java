package com.min.kr.jeonju_all.facility.model;

import android.content.Context;

import com.min.kr.jeonju_all.facility.data.FacilityTotalData;
import com.min.kr.jeonju_all.facility.data.HealthTotalData;
import com.min.kr.jeonju_all.facility.data.HospitalTotalData;
import com.min.kr.jeonju_all.facility.data.ParkTotalData;
import com.min.kr.jeonju_all.util.Variable;

import java.util.HashMap;

import io.reactivex.Maybe;

/**
 * Created by minkr on 2017-11-08.
 */

public class FacilityModel {
    Context mContext;
    public FacilityModel(Context mContext) {
        this.mContext = mContext;
    }

    public Maybe<FacilityTotalData> getPoliceList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return PoliceListApiService.Factory.create().getPoliceList(hashMap);
    }

    public Maybe<HospitalTotalData> getHospitalAllList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return HospitalListApiService.Factory.create().getHospitAllList(hashMap);
    }

    public Maybe<HospitalTotalData> getClinicList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return HospitalListApiService.Factory.create().getHospitInternalList(hashMap);
    }

    public Maybe<HospitalTotalData> getHospitalKoreaList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return HospitalListApiService.Factory.create().getHospitKoreaList(hashMap);
    }

    public Maybe<HospitalTotalData> getHospitalDentistList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return HospitalListApiService.Factory.create().getHospitDentistList(hashMap);
    }

    public Maybe<HospitalTotalData> getHospitalPostList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return HospitalListApiService.Factory.create().getHospitPostList(hashMap);
    }

    public Maybe<HospitalTotalData> getMedicineList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return HospitalListApiService.Factory.create().getMedicineList(hashMap);
    }

    public Maybe<ParkTotalData> getParkList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return ParkListApiService.Factory.create().getParkList(hashMap);
    }

    public Maybe<HealthTotalData> getHealthList(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("authApiKey", Variable._KINDFOOD_SERVICE_KEY);

        return HealthListApiService.Factory.create().getHealthList(hashMap);
    }
}
