package com.example.minkr.jeonju_all.house.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.house.data.HouseListData;
import com.example.minkr.jeonju_all.house.data.HouseTotalData;
import com.example.minkr.jeonju_all.house.model.HouseModel;
import com.example.minkr.jeonju_all.house.view.HouseView;
import com.example.minkr.jeonju_all.main.data.BookmarkList;
import com.example.minkr.jeonju_all.util.Logger;
import com.example.minkr.jeonju_all.util.sqlite.DBHelper;

import java.util.List;

import io.reactivex.Maybe;
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
    DBHelper dbHelper;

    @Override
    public void attachView(HouseView view) {
        this.view = view;
        this.mContext = view.getContext();
        model = new HouseModel(mContext);
        compositeDisposable = new CompositeDisposable();
        dbHelper = DBHelper.getInstance(mContext);
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

    public void getStoreInfo(HouseListData data) {
        view.getStoreInfo(data);
    }

    public void getAddressClick(HouseListData data) {
        view.getAddressClick(data);
    }

    public void getHouseDBData() {
        Disposable disposable = dbHelper.saveDBController.getDBData("숙박")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BookmarkList>>() {
                    @Override
                    public void accept(List<BookmarkList> bookmarkLists) throws Exception {
                        view.getHouseDBData(bookmarkLists);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void deleteDBData(HouseListData data) {
        Disposable disposable = Maybe.just(data)
                .map(new Function<HouseListData,HouseListData>() {
                    @Override
                    public HouseListData apply(HouseListData houseListData) throws Exception {
                        dbHelper.saveDBController.deleteHouse("숙박",houseListData);
                        houseListData.setLike(false);
                        return houseListData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HouseListData>() {
                    @Override
                    public void accept(HouseListData houseListData) throws Exception {
                        Logger.log("#45 houseListData DB삭제 ->" + houseListData.toString());
                        view.isLikeChangeData(houseListData);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void insertDBData(HouseListData data) {
        Disposable disposable = Maybe.just(data)
                .map(new Function<HouseListData,HouseListData>() {
                    @Override
                    public HouseListData apply(HouseListData houseListData) throws Exception {
                        dbHelper.saveDBController.addHouse(houseListData);
                        houseListData.setLike(true);
                        return houseListData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HouseListData>() {
                    @Override
                    public void accept(HouseListData houseListData) throws Exception {
                        Logger.log("#45 houseListData DB추가 ->" + houseListData.toString());
                        view.isLikeChangeData(houseListData);
                    }
                });

        compositeDisposable.add(disposable);
    }
    public void showDialog(HouseListData data) {
        view.showDialog(data);
    }
}
