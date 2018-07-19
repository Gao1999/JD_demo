package com.bwie.jd_demo.mvp.home.presenter;

import android.annotation.SuppressLint;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.home.model.HomeModel;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.home.model.bean.ClassBean;
import com.bwie.jd_demo.mvp.home.view.iview.HomeView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeView> {

    private HomeModel homeModel;

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @SuppressLint("CheckResult")
    public void AdFromDataNet() {
        homeModel.AdFromDataNet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AdBean adBean) {
                        if ("0".equals(adBean.getCode())) {
                            if (view != null) {
                                view.getSuccess(adBean);
                            }
                        } else {
                            if (view != null) {
                                view.getError("请求失败,请稍后再试...");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.getError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void ClassFromDataNet() {
        homeModel.ClassFromDataNet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ClassBean classBean) {
                        if ("0".equals(classBean.getCode())){
                            if (view!=null){
                                view.Success(classBean);
                            }
                        }else {
                            if (view!=null){
                                view.Error("请求失败...");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.Error(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
