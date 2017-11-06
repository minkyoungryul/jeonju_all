package com.example.minkr.jeonju_all.house.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.house.data.HouseListData;
import com.example.minkr.jeonju_all.house.data.HouseTotalData;
import com.example.minkr.jeonju_all.house.model.HouseModel;
import com.example.minkr.jeonju_all.house.view.HouseView;
import com.example.minkr.jeonju_all.util.Logger;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-11-06.
 */

public class HousePresenter implements Presenter<HouseView> {

    HouseView view;
    Context mContext;
    HouseModel model;
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(HouseView view) {
        this.view = view;
        this.mContext = view.getContext();
        model = new HouseModel(mContext);
        compositeDisposable = new CompositeDisposable();
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

    public void getHouseList(){
        Disposable disposable = model.getHouseList()
                .map(new Function<HouseTotalData, List<HouseListData>>() {
                    @Override
                    public List<HouseListData> apply(HouseTotalData houseTotalData) throws Exception {
                        return houseTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HouseListData>>() {
                    @Override
                    public void accept(List<HouseListData> houseListData) throws Exception {
                        Logger.log("#17 houseListData->"+houseListData.toString());
                        view.getHouseList(houseListData);
                    }
                });

        compositeDisposable.add(disposable);
    }
}
