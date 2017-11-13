package com.min.kr.jeonju_all.facility.presenter;

import android.content.Context;

import com.min.kr.jeonju_all.common.presenter.Presenter;
import com.min.kr.jeonju_all.facility.data.HospitalListData;
import com.min.kr.jeonju_all.facility.data.HospitalTotalData;
import com.min.kr.jeonju_all.facility.model.FacilityModel;
import com.min.kr.jeonju_all.facility.view.HospitalView;
import com.min.kr.jeonju_all.util.Logger;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by minkr on 2017-11-08.
 */

public class HospitalPresenter implements Presenter<HospitalView> {

    Context mContext;
    HospitalView view;
    CompositeDisposable compositeDisposable;
    FacilityModel model;

    @Override
    public void attachView(HospitalView view) {
        this.view = view;
        this.mContext = view.getContext();
        compositeDisposable = new CompositeDisposable();
        model = new FacilityModel(mContext);
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

    public void getHospitalList() {
        Disposable disposable = model.getHospitalAllList()
                .map(new Function<HospitalTotalData, List<HospitalListData>>() {
                    @Override
                    public List<HospitalListData> apply(HospitalTotalData hospitalTotalData) throws Exception {
                        return hospitalTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HospitalListData>>() {
                    @Override
                    public void accept(List<HospitalListData> hospitalListData) throws Exception {
                        Logger.log("#23 병원 data->"+hospitalListData.toString());
                        view.getHospitalList(hospitalListData);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void getClinicList() {
        Disposable disposable = model.getClinicList()
                .map(new Function<HospitalTotalData, List<HospitalListData>>() {
                    @Override
                    public List<HospitalListData> apply(HospitalTotalData hospitalTotalData) throws Exception {
                        return hospitalTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HospitalListData>>() {
                    @Override
                    public void accept(List<HospitalListData> hospitalListData) throws Exception {
                        Logger.log("#24 의원 data->"+hospitalListData.toString());
                        view.getClinicList(hospitalListData);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void getOriginalList() { //한의원
        Disposable disposable = model.getHospitalKoreaList()
                .map(new Function<HospitalTotalData, List<HospitalListData>>() {
                    @Override
                    public List<HospitalListData> apply(HospitalTotalData hospitalTotalData) throws Exception {
                        return hospitalTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HospitalListData>>() {
                    @Override
                    public void accept(List<HospitalListData> hospitalListData) throws Exception {
                        Logger.log("#25 한의원 data->"+hospitalListData.toString());
                        view.getOriginalList(hospitalListData);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void getPostpartumList() {//산후조리원
        Disposable disposable = model.getHospitalPostList()
                .map(new Function<HospitalTotalData, List<HospitalListData>>() {
                    @Override
                    public List<HospitalListData> apply(HospitalTotalData hospitalTotalData) throws Exception {
                        return hospitalTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HospitalListData>>() {
                    @Override
                    public void accept(List<HospitalListData> hospitalListData) throws Exception {
                        Logger.log("#26 산후조리원 data->"+hospitalListData.toString());
                        view.getPostpartumList(hospitalListData);
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void getDentistList() {
        Disposable disposable = model.getHospitalDentistList()
                .map(new Function<HospitalTotalData, List<HospitalListData>>() {
                    @Override
                    public List<HospitalListData> apply(HospitalTotalData hospitalTotalData) throws Exception {
                        return hospitalTotalData.getBody().getData().getList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HospitalListData>>() {
                    @Override
                    public void accept(List<HospitalListData> hospitalListData) throws Exception {
                        Logger.log("#27 치과의원 data->"+hospitalListData.toString());
                        view.getDentistList(hospitalListData);
                    }
                });
        compositeDisposable.add(disposable);
    }
}
