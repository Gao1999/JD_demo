package com.bwie.jd_demo.mvp.shopping.presenter;

import android.annotation.SuppressLint;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.shopping.model.AddressModel;
import com.bwie.jd_demo.mvp.shopping.model.bean.AddadderssBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SelectAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SetAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataAddrBean;
import com.bwie.jd_demo.mvp.shopping.view.iview.AddressView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddressPresenter extends BasePresenter<AddressView> {

    private AddressModel addressModel;

    public AddressPresenter(AddressView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        addressModel = new AddressModel();
    }

    @SuppressLint("CheckResult")
    public void SelectAddressNet(int uid) {
        addressModel.SelectAddressNet(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SelectAddressBean selectAddressBean) {
                        if ("0".equals(selectAddressBean.getCode())) {
                            if (view != null) {
                                view.AddressSuccess(selectAddressBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("获取地址失败!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("获取地址失败");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void addAddrNet(int uid, String dizi, String model, String name) {
        addressModel.addAddrNet(uid, dizi, model, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddadderssBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AddadderssBean addadderssBean) {
                        if ("0".equals(addadderssBean.getCode())) {
                            if (view != null) {
                                view.AddaddressSuccess(addadderssBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("添加地址失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("添加地址失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void UpdataAddrNet(int uid, int addrid, String dizi, String model, String name) {
        addressModel.UpdataAddrNet(uid, addrid, dizi, model, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdataAddrBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpdataAddrBean updataAddrBean) {
                        if ("0".equals(updataAddrBean.getCode())) {
                            if (view != null) {
                                view.UpdataAddrSuccess(updataAddrBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("修改地址失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("修改地址失败");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void SetAddressNet(int uid, int addrid, int status) {
        addressModel.SetAddressNet(uid, addrid, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SetAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SetAddressBean setAddressBean) {
                        if ("0".equals(setAddressBean.getCode())) {
                            if (view != null) {
                                view.SetAddressSuccess(setAddressBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("设置默认地址失败!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("设置默认地址失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
