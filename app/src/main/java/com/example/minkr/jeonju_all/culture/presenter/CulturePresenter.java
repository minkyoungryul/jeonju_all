package com.example.minkr.jeonju_all.culture.presenter;

import android.content.Context;

import com.example.minkr.jeonju_all.common.presenter.Presenter;
import com.example.minkr.jeonju_all.culture.data.CultureListData;
import com.example.minkr.jeonju_all.culture.data.CultureTotalData;
import com.example.minkr.jeonju_all.culture.model.CultureModel;
import com.example.minkr.jeonju_all.culture.view.CultureView;

import java.util.List;

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

    @Override
    public void attachView(CultureView view) {
        this.view = view;
        mContext = view.getContext();
        compositeDisposable = new CompositeDisposable();
        model = new CultureModel(mContext);
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
}
