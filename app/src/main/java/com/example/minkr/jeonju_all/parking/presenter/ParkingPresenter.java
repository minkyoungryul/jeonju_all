package com.example.minkr.jeonju_all.parking.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.parking.data.ParkingListData;
import com.example.minkr.jeonju_all.parking.data.ParkingTotalData;
import com.example.minkr.jeonju_all.parking.model.ParkingModel;
import com.example.minkr.jeonju_all.parking.view.ParkingVIew;
import com.example.minkr.jeonju_all.util.Logger;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-10-22.
 */

public class ParkingPresenter implements Presenter<ParkingVIew> {

    ParkingVIew view;
    Context mContext;
    ParkingModel model;
    CompositeDisposable compositeDisposable;
    @Override
    public void attachView(ParkingVIew view) {
        this.view = view;
        mContext = view.getContext();
        model = new ParkingModel(mContext);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.clear();
    }

    @Override
    public void notConnectNetworking() {

    }

    public void getParkingList(){
        Disposable disposable = model.getParkingList()
                .map(new Function<ParkingTotalData, List<ParkingListData>>() {
                    @Override
                    public List<ParkingListData> apply(ParkingTotalData parkingTotalData) throws Exception {
                        return parkingTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ParkingListData>>() {
                    @Override
                    public void accept(List<ParkingListData> parkingListData) throws Exception {
                        Logger.log("#30 주차장 data->"+parkingListData.toString());
                        view.getParkingList(parkingListData);
                    }
                });

        compositeDisposable.add(disposable);
    }
}
