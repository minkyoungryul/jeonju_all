package com.example.minkr.jeonju_all.kindFood.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodDatas;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodListData;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodTotalData;
import com.example.minkr.jeonju_all.kindFood.model.KindFoodModel;
import com.example.minkr.jeonju_all.kindFood.view.KindFoodVIew;
import com.example.minkr.jeonju_all.util.Logger;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-10-22.
 */

public class KindFoodPresenter implements Presenter<KindFoodVIew> {
    KindFoodVIew view;
    KindFoodModel model;
    Context mContext;
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(KindFoodVIew view) {
        this.view = view;
        mContext = view.getContext();
        model = new KindFoodModel(mContext);
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


    public void getKindFoodList(){
        Disposable disposable = model.getKindFoodList()
                .toFlowable()
                .flatMap(new Function<KindFoodTotalData, Flowable<List<KindFoodListData>>>() {
                    @Override
                    public Flowable<List<KindFoodListData>> apply(KindFoodTotalData kindFoodTotalData) throws Exception {
//                        if(kindFoodTotalData.getBody().getData().getList().get(2) != null
//                                &&kindFoodTotalData.getBody().getData().getList().get(2).getName().equals("제일크리너스샵")){
//                            kindFoodTotalData.getBody().getData().getList().remove(2);
//                        }

                        return Flowable.just(kindFoodTotalData.getBody().getData().getList());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<KindFoodListData>>() {
                    @Override
                    public void accept(List<KindFoodListData> kindFoodListDatas) throws Exception {
                        Logger.log("#5 kindFoodDatas ->"+ kindFoodListDatas.toString());

                        view.getKindFoodDatas(kindFoodListDatas);
                    }
                });
        compositeDisposable.add(disposable);

    }

    public void getFoodStoreInfo(KindFoodListData data) {
        view.getFoodStoreInfo(data);
    }

    public void showDialog() {
        view.showDialog();
    }
}
