package com.example.minkr.jeonju_all.main.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodDatas;
import com.example.minkr.jeonju_all.kindFood.data.KindFoodTotalData;
import com.example.minkr.jeonju_all.kindFood.model.KindFoodModel;
import com.example.minkr.jeonju_all.main.BookmarkList;
import com.example.minkr.jeonju_all.main.view.MainView;
import com.example.minkr.jeonju_all.util.Logger;
import com.example.minkr.jeonju_all.util.sqlite.DBHelper;

import java.util.List;

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
    DBHelper dbHelper;

    @Override
    public void attachView(MainView view) {
        this.view = view;
        mContext = view.getContext();
        kindFoodModel = new KindFoodModel(mContext);
        compositeDisposable = new CompositeDisposable();
        dbHelper = DBHelper.getInstance(mContext);
        dbHelper.openDB();
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.clear();
        dbHelper.closeDB();
    }

    @Override
    public void notConnectNetworking() {
        view.notConnectNetworking();
    }

    public void getBookmarkList() {
        Disposable disposable = dbHelper.saveDBController.getAllBookMarkList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BookmarkList>>() {
                    @Override
                    public void accept(List<BookmarkList> bookmarkLists) throws Exception {
                        view.getBookmarkList(bookmarkLists);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void showStoreInfo(BookmarkList data) {
        view.showStoreInfo(data);
    }

    public void allDeleteData() {
        int empty = 0;
        Disposable disposable = Maybe.just(empty)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer empty) throws Exception {
                        dbHelper.saveDBController.deleteAllData();
                        return empty;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        view.allDeleteData();
                    }
                });

    }

    public void showDeleteDialog(BookmarkList data) {
        view.showDeleteDialog(data);
    }

    public void getAddressClick(BookmarkList data){
        view.getAddressClick(data);
    }

    public void deleteData(BookmarkList data) {
        Disposable disposable = Maybe.just(data)
                .map(new Function<BookmarkList, BookmarkList>() {
                    @Override
                    public BookmarkList apply(BookmarkList bookmarkList) throws Exception {
                        dbHelper.saveDBController.deleteBookmarkData(bookmarkList);
                        return bookmarkList;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookmarkList>() {
                    @Override
                    public void accept(BookmarkList bookmarkList) throws Exception {
                        view.deleteData(bookmarkList);
                    }
                });

        compositeDisposable.add(disposable);
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
