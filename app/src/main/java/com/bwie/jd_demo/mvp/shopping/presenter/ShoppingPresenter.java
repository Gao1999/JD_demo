package com.bwie.jd_demo.mvp.shopping.presenter;

import android.annotation.SuppressLint;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.shopping.model.ShoppingModel;
import com.bwie.jd_demo.mvp.shopping.model.bean.DeleteCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpDataCartBean;
import com.bwie.jd_demo.mvp.shopping.view.iview.ShoppingView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingPresenter extends BasePresenter<ShoppingView> {

    private ShoppingModel shoppingModel;

    public ShoppingPresenter(ShoppingView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        shoppingModel = new ShoppingModel();
    }


    public void ShoppingNetData(int uid) {
        shoppingModel.ShoppingNetData(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ShoppingBean shoppingBean) {
                        if ("0".equals(shoppingBean.getCode())) {
                            if (view != null) {
                                view.Success(shoppingBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("请求数据失败...");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void deleteCart(int uid, int pid) {
        shoppingModel.deleteCart(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DeleteCartBean deleteCartBean) {
                        if ("0".equals(deleteCartBean.getCode())) {
                            if (view != null) {
                                view.SUCCESS(deleteCartBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("请求错误");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("请求错误");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void UpDataCartNet(int uid, int sellerid, int pid, int num, int Selected) {
        shoppingModel.UpDataCartNet(uid, sellerid, pid, num, Selected)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpDataCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpDataCartBean upDataCartBean) {
                        if ("0".equals(upDataCartBean.getCode())) {
                            if (view != null) {
                                view.UpDataCartSuccess(upDataCartBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("请求失败!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("请求失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
