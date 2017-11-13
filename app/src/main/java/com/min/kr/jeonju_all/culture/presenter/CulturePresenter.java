package com.min.kr.jeonju_all.culture.presenter;

import android.content.Context;

import com.min.kr.jeonju_all.common.presenter.Presenter;
import com.min.kr.jeonju_all.culture.data.CultureListData;
import com.min.kr.jeonju_all.culture.data.CultureTotalData;
import com.min.kr.jeonju_all.culture.model.CultureModel;
import com.min.kr.jeonju_all.culture.view.CultureView;
import com.min.kr.jeonju_all.main.data.BookmarkList;
import com.min.kr.jeonju_all.util.Logger;
import com.min.kr.jeonju_all.util.sqlite.DBHelper;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-11-07.
 */

public class CulturePresenter implements Presenter<CultureView> {

    CultureView view;
    Context mContext;
    CompositeDisposable compositeDisposable;
    CultureModel model;
    DBHelper dbHelper;

    @Override
    public void attachView(CultureView view) {
        this.view = view;
        mContext = view.getContext();
        compositeDisposable = new CompositeDisposable();
        model = new CultureModel(mContext);
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

    public void getCultureList(){
        Disposable disposable = model.getCultureList()
                .map(new Function<CultureTotalData, List<CultureListData>>() {
                    @Override
                    public List<CultureListData> apply(CultureTotalData cultureTotalData) throws Exception {
                        return cultureTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CultureListData>>() {
                    @Override
                    public void accept(List<CultureListData> cultureListData) throws Exception {
                        view.getCultureList(cultureListData);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void showInfo(CultureListData data) {
        view.showInfo(data);
    }

    public void getAddressClick(CultureListData data){
        view.getAddressClick(data);
    }

    public void getCultureDB() {
        Disposable disposable = dbHelper.saveDBController.getDBData("문화")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BookmarkList>>() {
                    @Override
                    public void accept(List<BookmarkList> bookmarkLists) throws Exception {
                        view.getCultureDBData(bookmarkLists);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void insertDBData(CultureListData data) {
        Disposable disposable = Maybe.just(data)
                .map(new Function<CultureListData, CultureListData>() {
                    @Override
                    public CultureListData apply(CultureListData cultureListData) throws Exception {
                        dbHelper.saveDBController.addCulture(cultureListData);
                        cultureListData.setLike(true);
                        return cultureListData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CultureListData>() {
                    @Override
                    public void accept(CultureListData cultureListData) throws Exception {
                        Logger.log("#46 cultureListData DB추가 ->"+cultureListData.toString());
                        view.isLikeChangeData(cultureListData);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void deleteDBData(CultureListData data) {
        Disposable disposable = Maybe.just(data)
                .map(new Function<CultureListData, CultureListData>() {
                    @Override
                    public CultureListData apply(CultureListData cultureListData) throws Exception {
                        dbHelper.saveDBController.deleteCulture("문화",cultureListData);
                        cultureListData.setLike(false);
                        return cultureListData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CultureListData>() {
                    @Override
                    public void accept(CultureListData cultureListData) throws Exception {
                        Logger.log("#46 cultureListData DB삭제 ->"+cultureListData.toString());
                        view.isLikeChangeData(cultureListData);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void showDialog(CultureListData data) {
        view.showDialog(data);
    }
}
