package com.bwie.jd_demo.mvp.home.presenter;

import android.annotation.SuppressLint;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.home.model.GoodsModel;
import com.bwie.jd_demo.mvp.home.model.bean.AddCartBean;
import com.bwie.jd_demo.mvp.home.model.bean.GoodsDetailBean;
import com.bwie.jd_demo.mvp.home.model.bean.SearchBean;
import com.bwie.jd_demo.mvp.home.view.iview.IGoodsDetailsView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GoodsPresenter extends BasePresenter<IGoodsDetailsView> {

    private GoodsModel goodsModel;

    public GoodsPresenter(IGoodsDetailsView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        goodsModel = new GoodsModel();
    }

    @SuppressLint("CheckResult")
    public void GoodsXbannerNet(int pid) {
        goodsModel.GoodsXbannerNet(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GoodsDetailBean goodsDetailBean) {
                        if ("0".equals(goodsDetailBean.getCode())) {
                            if (view != null) {
                                view.SUCCESS(goodsDetailBean);
                            }
                        } else {
                            if (view != null) {
                                view.ERROR("请求失败!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.ERROR("请求失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void AddCartNet(int uid, int pid) {
        goodsModel.AddCartNet(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AddCartBean addCartBean) {
                        if ("0".equals(addCartBean.getCode())) {
                            if (view != null) {
                                view.GETSUCCESS(addCartBean);
                            }
                        } else {
                            if (view != null) {
                                view.GETERROR("请求失败!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.GETERROR("请求失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void SearchNet(String keywords, int page, int sort) {
        goodsModel.SearchNet(keywords, page, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        if ("0".equals(searchBean.getCode())) {
                            if (view != null) {
                                view.SeachSuccess(searchBean);
                            }
                        } else {
                            if (view != null) {
                                view.ERROR("请求失败!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.ERROR("请求失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
