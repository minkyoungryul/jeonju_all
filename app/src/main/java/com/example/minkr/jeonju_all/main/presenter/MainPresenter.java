package com.example.minkr.jeonju_all.main.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodDatas;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodTotalData;
import com.example.minkr.jeonju_all.kindFood.model.KindFoodModel;
import com.example.minkr.jeonju_all.main.view.MainView;
import com.example.minkr.jeonju_all.util.Logger;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-10-25.
 */

public class MainPresenter implements Presenter<MainView> {
    MainView view;
    KindFoodModel kindFoodModel;
    Context mContext;
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(MainView view) {
        this.view = view;
        mContext = view.getContext();
        kindFoodModel = new KindFoodModel(mContext);
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

//    public void getKindFoodList(){
//        Disposable disposable = kindFoodModel.getKindFoodList()
//                .flatMap(new Function<KindFoodTotalData, MaybeSource<KindFoodDatas>>() {
//                    @Override
//                    public MaybeSource<KindFoodDatas> apply(KindFoodTotalData kindFoodTotalData) throws Exception {
//                        if(kindFoodTotalData.getBody().getData().getList().get(2) != null
//                                &&kindFoodTotalData.getBody().getData().getList().get(2).getName().equals("제일크리너스샵")){
//                            kindFoodTotalData.getBody().getData().getList().remove(2);
//                        }
//
//                        return Maybe.just(kindFoodTotalData.getBody().getData());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<KindFoodDatas>() {
//                    @Override
//                    public void accept(KindFoodDatas kindFoodDatas) throws Exception {
//                        Logger.log("#5 kindFoodDatas ->"+ kindFoodDatas.toString());
//                    }
//                });
//        compositeDisposable.add(disposable);
//
//    }
}
