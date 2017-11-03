package com.example.minkr.jeonju_all.food.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.food.data.FoodTotalData;
import com.example.minkr.jeonju_all.food.model.FoodModel;
import com.example.minkr.jeonju_all.food.view.FoodView;
import com.example.minkr.jeonju_all.util.Logger;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-11-03.
 */

public class FoodPresenter implements Presenter<FoodView> {

    FoodView view;
    Context mContext;
    CompositeDisposable compositeDisposable;
    FoodModel model;

    @Override
    public void attachView(FoodView view) {
        this.view = view;
        mContext = view.getContext();
        compositeDisposable = new CompositeDisposable();
        model = new FoodModel(mContext);
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

    public void getFoodRiceList() {
        Disposable disposable = model.getFoodRiceList()
                .map(new Function<FoodTotalData, List<FoodListData>>() {
                    @Override
                    public List<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FoodListData>>() {
                    @Override
                    public void accept(List<FoodListData> foodListData) throws Exception {
                        Logger.log("#1 #13 foodListData->" + foodListData.toString());
                        view.getFoodRiceDatas(foodListData);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void getFoodBibimbapList() {
        Disposable disposable = model.getFoodBibimbapList()
                .map(new Function<FoodTotalData, List<FoodListData>>() {
                    @Override
                    public List<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FoodListData>>() {
                    @Override
                    public void accept(List<FoodListData> foodListData) throws Exception {
                        Logger.log("#1 #14 foodListData->"+foodListData.toString());
                        view.getFoodBibimbapDatas(foodListData);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void getFoodKongbapList() {
        Disposable disposable = model.getFoodKongbapList()
                .map(new Function<FoodTotalData, List<FoodListData>>() {
                    @Override
                    public List<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FoodListData>>() {
                    @Override
                    public void accept(List<FoodListData> foodListData) throws Exception {
                        Logger.log("#1 #15 foodListData->"+foodListData.toString());
                        view.getFoodKongbapDatas(foodListData);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void getFoodWineList() {
        Disposable disposable = model.getFoodWineList()
                .map(new Function<FoodTotalData, List<FoodListData>>() {
                    @Override
                    public List<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FoodListData>>() {
                    @Override
                    public void accept(List<FoodListData> foodListData) throws Exception {
                        Logger.log("#1 #16 foodListData->"+foodListData.toString());
                        view.getFoodWineDatas(foodListData);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void getFoodHanokList() {
        Disposable disposable = model.getFoodHanokList()
                .map(new Function<FoodTotalData, List<FoodListData>>() {
                    @Override
                    public List<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FoodListData>>() {
                    @Override
                    public void accept(List<FoodListData> foodListData) throws Exception {
                        Logger.log("#1 #16 foodListData->"+foodListData.toString());
                        view.getFoodHanokDatas(foodListData);
                    }
                });
        compositeDisposable.add(disposable);
    }
}
