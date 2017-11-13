package com.example.minkr.jeonju_all.facility.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.facility.data.FacilityListData;
import com.example.minkr.jeonju_all.facility.data.FacilityTotalData;
import com.example.minkr.jeonju_all.facility.model.FacilityModel;
import com.example.minkr.jeonju_all.facility.view.PoliceView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-11-08.
 */

public class PolicePresenter implements Presenter<PoliceView> {

    PoliceView view;
    Context mContext;
    CompositeDisposable compositeDisposable;
    FacilityModel model;

    @Override
    public void attachView(PoliceView view) {
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
        view.getContext();
    }

    public void getPoliceList(){
        Disposable disposable = model.getPoliceList()
                .map(new Function<FacilityTotalData, List<FacilityListData>>() {
                    @Override
                    public List<FacilityListData> apply(FacilityTotalData facilityTotalData) throws Exception {
                        return facilityTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FacilityListData>>() {
                    @Override
                    public void accept(List<FacilityListData> facilityListData) throws Exception {
                        view.getPoliceList(facilityListData);
                    }
                });

        compositeDisposable.add(disposable);
    }
}
