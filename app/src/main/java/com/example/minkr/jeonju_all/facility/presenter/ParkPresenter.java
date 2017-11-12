package com.example.minkr.jeonju_all.facility.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.facility.data.HospitalListData;
import com.example.minkr.jeonju_all.facility.data.HospitalTotalData;
import com.example.minkr.jeonju_all.facility.data.ParkListData;
import com.example.minkr.jeonju_all.facility.data.ParkTotalData;
import com.example.minkr.jeonju_all.facility.model.FacilityModel;
import com.example.minkr.jeonju_all.facility.view.MedicineView;
import com.example.minkr.jeonju_all.facility.view.ParkView;
import com.example.minkr.jeonju_all.util.Logger;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-11-08.
 */

public class ParkPresenter implements Presenter<ParkView> {

    Context mContext;
    ParkView view;
    CompositeDisposable compositeDisposable;
    FacilityModel model;

    @Override
    public void attachView(ParkView view) {
        this.view = view;
        this.mContext = view.getContext();
        compositeDisposable = new CompositeDisposable();
        model = new FacilityModel(mContext);
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.clear();
    }

    @Override
    public void notConnectNetworking() {
        view.notConnectNetworking();
    }

    public void getParkList() {
        Disposable disposable = model.getParkList()
                .map(new Function<ParkTotalData, List<ParkListData>>() {
                    @Override
                    public List<ParkListData> apply(ParkTotalData parkTotalData) throws Exception {
                        return parkTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ParkListData>>() {
                    @Override
                    public void accept(List<ParkListData> parkListData) throws Exception {
                        Logger.log("#29 공원 data->"+parkListData.toString());
                        view.getParkList(parkListData);
                    }
                });
        compositeDisposable.add(disposable);
    }
}
