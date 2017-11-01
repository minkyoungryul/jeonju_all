package com.example.minkr.jeonju_all.parking.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.parking.data.ParkingTotalData;
import com.example.minkr.jeonju_all.parking.model.ParkingModel;
import com.example.minkr.jeonju_all.parking.view.ParkingVIew;
import com.example.minkr.jeonju_all.util.Logger;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ParkingTotalData>() {
                    @Override
                    public void accept(ParkingTotalData parkingTotalData) throws Exception {
                        Logger.log("#1 ParkingTotalData -> "+parkingTotalData.toString() );
                    }
                });

        compositeDisposable.add(disposable);
    }
}
