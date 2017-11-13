package com.min.kr.jeonju_all.food.presenter;

import android.content.Context;

import com.min.kr.jeonju_all.common.presenter.Presenter;
import com.min.kr.jeonju_all.food.data.FoodListData;
import com.min.kr.jeonju_all.food.view.FoodDetailView;
import com.min.kr.jeonju_all.util.Logger;
import com.min.kr.jeonju_all.util.sqlite.DBHelper;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-11-03.
 */

public class FoodDetailPresenter implements Presenter<FoodDetailView> {

    FoodDetailView view;
    Context mContext;
    DBHelper dbHelper;
    CompositeDisposable compositeDisposable;

    @Override
    public void attachView(FoodDetailView view) {
        this.view = view;
        mContext = view.getContext();
        this.dbHelper = DBHelper.getInstance(mContext);
        this.compositeDisposable = new CompositeDisposable();
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

    public void getStoreInfo(FoodListData data) {
        view.getStoreInfo(data);
    }

    public void getAddressClick(FoodListData data) {
        view.getAddressClick(data);
    }

    public void insertDBData(FoodListData data) {
        Disposable disposable = Maybe.just(data)
                .map(new Function<FoodListData, FoodListData>() {
                    @Override
                    public FoodListData apply(FoodListData foodListData) throws Exception {
                        dbHelper.saveDBController.addFood(foodListData);
                        foodListData.setLike(true);
                        return foodListData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FoodListData>() {
                    @Override
                    public void accept(FoodListData foodListData) throws Exception {
                        Logger.log("#41 foodListData DB추가 ->"+foodListData.toString());
                        view.isLikeChangeData(foodListData);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void deleteDBData(FoodListData data) {
        Disposable disposable = Maybe.just(data)
                .map(new Function<FoodListData, FoodListData>() {
                    @Override
                    public FoodListData apply(FoodListData foodListData) throws Exception {
                        dbHelper.saveDBController.deleteFood("음식",foodListData);
                        foodListData.setLike(false);
                        return foodListData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FoodListData>() {
                    @Override
                    public void accept(FoodListData foodListData) throws Exception {
                        Logger.log("#41 foodListData DB삭제 ->"+foodListData.toString());
                        view.isLikeChangeData(foodListData);
                    }
                });

        compositeDisposable.add(disposable);
    }
    public void showDialog(FoodListData data) {
        view.showDialog(data);
    }
}
