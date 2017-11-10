package com.example.minkr.jeonju_all.food.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.food.data.FoodListData;
import com.example.minkr.jeonju_all.food.data.FoodTotalData;
import com.example.minkr.jeonju_all.food.model.FoodModel;
import com.example.minkr.jeonju_all.food.view.FoodView;
import com.example.minkr.jeonju_all.util.Logger;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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

    public FoodListData getFoodListData(FoodListData foodListData, String type){
        FoodListData data = new FoodListData();
        data.setType(type);
        data.setCloseTime(foodListData.getCloseTime());
        data.setHoliday(foodListData.getHoliday());
        data.setIsParking(foodListData.getIsParking());
        data.setIsReserve(foodListData.getIsReserve());
        data.setMainImg(foodListData.getMainImg());
        data.setMainMenu(foodListData.getMainMenu());
        data.setNewAddr(foodListData.getNewAddr());
        data.setOpenTime(foodListData.getOpenTime());
        data.setParkingDetail(foodListData.getParkingDetail());
        data.setParkingInfo(foodListData.getParkingInfo());
        data.setPosX(foodListData.getPosX());
        data.setPosY(foodListData.getPosY());
        data.setSeatCnt(foodListData.getSeatCnt());
        data.setStoreName(foodListData.getStoreName());
        data.setTableCnt(foodListData.getTableCnt());
        data.setTel(foodListData.getTel());
        return data;
    }

    public void getFoodRiceList() {
        Disposable disposable = model.getFoodRiceList()
                .filter(new Predicate<FoodTotalData>() {
                    @Override
                    public boolean test(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData == null ? false : true;
                    }
                })
                .toFlowable()
                .flatMap(new Function<FoodTotalData, Flowable<FoodListData>>() {
                    @Override
                    public Flowable<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return Flowable.fromIterable(foodTotalData.getBody().getData().getList());
                    }
                })
                .map(new Function<FoodListData, FoodListData>() {
                    @Override
                    public FoodListData apply(FoodListData foodListData) throws Exception {
                        return getFoodListData(foodListData, "한정식");
                    }
                })
                .subscribeOn(Schedulers.io())
                .toList()
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
                .filter(new Predicate<FoodTotalData>() {
                    @Override
                    public boolean test(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData == null ? false : true;
                    }
                })
                .toFlowable()
                .flatMap(new Function<FoodTotalData, Flowable<FoodListData>>() {
                    @Override
                    public Flowable<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return Flowable.fromIterable(foodTotalData.getBody().getData().getList());
                    }
                })
                .map(new Function<FoodListData, FoodListData>() {
                    @Override
                    public FoodListData apply(FoodListData foodListData) throws Exception {
                        return getFoodListData(foodListData, "전주 비빔밥");
                    }
                })
                .subscribeOn(Schedulers.io())
                .toList()
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
                .filter(new Predicate<FoodTotalData>() {
                    @Override
                    public boolean test(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData == null ? false : true;
                    }
                })
                .toFlowable()
                .flatMap(new Function<FoodTotalData, Flowable<FoodListData>>() {
                    @Override
                    public Flowable<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return Flowable.fromIterable(foodTotalData.getBody().getData().getList());
                    }
                })
                .map(new Function<FoodListData, FoodListData>() {
                    @Override
                    public FoodListData apply(FoodListData foodListData) throws Exception {
                        return getFoodListData(foodListData, "콩나물국밥");
                    }
                })
                .subscribeOn(Schedulers.io())
                .toList()
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
                .filter(new Predicate<FoodTotalData>() {
                    @Override
                    public boolean test(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData == null ? false : true;
                    }
                })
                .toFlowable()
                .flatMap(new Function<FoodTotalData, Flowable<FoodListData>>() {
                    @Override
                    public Flowable<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return Flowable.fromIterable(foodTotalData.getBody().getData().getList());
                    }
                })
                .map(new Function<FoodListData, FoodListData>() {
                    @Override
                    public FoodListData apply(FoodListData foodListData) throws Exception {
                        return getFoodListData(foodListData, "막걸리");
                    }
                })
                .subscribeOn(Schedulers.io())
                .toList()
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
                .filter(new Predicate<FoodTotalData>() {
                    @Override
                    public boolean test(FoodTotalData foodTotalData) throws Exception {
                        return foodTotalData == null ? false : true;
                    }
                })
                .toFlowable()
                .flatMap(new Function<FoodTotalData, Flowable<FoodListData>>() {
                    @Override
                    public Flowable<FoodListData> apply(FoodTotalData foodTotalData) throws Exception {
                        return Flowable.fromIterable(foodTotalData.getBody().getData().getList());
                    }
                })
                .map(new Function<FoodListData, FoodListData>() {
                    @Override
                    public FoodListData apply(FoodListData foodListData) throws Exception {
                        return getFoodListData(foodListData, "한옥마을 맛집");
                    }
                })
                .subscribeOn(Schedulers.io())
                .toList()
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

    public void getStoreInfo(FoodListData data) {
        view.getStoreInfo(data);
    }

    public void getAddressClick(FoodListData data) {
        view.getAddressClick(data);
    }
}
