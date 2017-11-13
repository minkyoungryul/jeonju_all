package com.example.minkr.jeonju_all.facility.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.facility.data.HospitalListData;
import com.example.minkr.jeonju_all.facility.data.HospitalTotalData;
import com.example.minkr.jeonju_all.facility.model.FacilityModel;
import com.example.minkr.jeonju_all.facility.view.HospitalView;
import com.example.minkr.jeonju_all.facility.view.MedicineView;
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

public class MedicinePresenter implements Presenter<MedicineView> {

    Context mContext;
    MedicineView view;
    CompositeDisposable compositeDisposable;
    FacilityModel model;

    @Override
    public void attachView(MedicineView view) {
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

    public void getMedicineList() {
        Disposable disposable = model.getMedicineList()
                .map(new Function<HospitalTotalData, List<HospitalListData>>() {
                    @Override
                    public List<HospitalListData> apply(HospitalTotalData hospitalTotalData) throws Exception {
                        return hospitalTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HospitalListData>>() {
                    @Override
                    public void accept(List<HospitalListData> hospitalListData) throws Exception {
                        Logger.log("#28 약국 data->"+hospitalListData.toString());
                        view.getMedicineList(hospitalListData);
                    }
                });

        compositeDisposable.add(disposable);
    }
}
