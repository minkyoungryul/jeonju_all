package com.min.kr.jeonju_all.facility.presenter;

import android.content.Context;

import com.min.kr.jeonju_all.common.presenter.Presenter;
import com.min.kr.jeonju_all.facility.data.HealthListData;
import com.min.kr.jeonju_all.facility.data.HealthTotalData;
import com.min.kr.jeonju_all.facility.model.FacilityModel;
import com.min.kr.jeonju_all.facility.view.HealthView;
import com.min.kr.jeonju_all.util.Logger;

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

public class HealthPresenter implements Presenter<HealthView> {

    Context mContext;
    HealthView view;
    CompositeDisposable compositeDisposable;
    FacilityModel model;

    @Override
    public void attachView(HealthView view) {
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

    public void getHealthList() {
        Disposable disposable = model.getHealthList()
                .map(new Function<HealthTotalData, List<HealthListData>>() {
                    @Override
                    public List<HealthListData> apply(HealthTotalData healthTotalData) throws Exception {
                        return healthTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HealthListData>>() {
                    @Override
                    public void accept(List<HealthListData> healthListData) throws Exception {
                        Logger.log("#31 체육시설 data->"+healthListData.toString());
                        view.getHealthList(healthListData);
                    }
                });

        compositeDisposable.add(disposable);
    }
}
