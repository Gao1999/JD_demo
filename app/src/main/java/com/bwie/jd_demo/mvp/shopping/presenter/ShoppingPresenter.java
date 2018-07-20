package com.bwie.jd_demo.mvp.shopping.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.shopping.model.ShoppingModel;
import com.bwie.jd_demo.mvp.shopping.model.bean.CreateOrderBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DefaultAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DeleteCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.OrdersBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpDataCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataOrderBean;
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

    @SuppressLint("CheckResult")
    public void CreateOrderNet(int uid, double price) {
        shoppingModel.CreateOrderNet(uid, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CreateOrderBean createOrderBean) {
                        if ("0".equals(createOrderBean.getCode())) {
                            if (view != null) {
                                view.CreateOrdeSuccess(createOrderBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("创建失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("创建失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void SelectOrderNet(int uid,int status) {
        shoppingModel.SelectOrderNet(uid,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrdersBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(OrdersBean ordersBean) {
                        Log.e("tag", "onNext: " + ordersBean.getCode());

                        if (view != null) {
                            view.SelectOrderNetSuccess(ordersBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", "onError: " + e.getMessage());
                        if (view != null) {
                            view.Error("请求失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void UpdateOrderNetFrom(int uid, int status, int orderId) {
        shoppingModel.UpdateOrderNetFrom(uid, status, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdataOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpdataOrderBean updataOrderBean) {
                        if ("0".equals(updataOrderBean.getCode())) {
                            if (view != null) {
                                view.UpdateOrderNetSuccess(updataOrderBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("修改订单失败.");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("修改订单失败");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void DefaultAddressNet(int uid) {
        shoppingModel.DefaultAddressNet(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DefaultAddressBean defaultAddressBean) {
                        if ("0".equals(defaultAddressBean.getCode())) {
                            if (view != null) {
                                view.DefaultAddrSuccess(defaultAddressBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("获取地址失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("获取地址失败!");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
