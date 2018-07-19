package com.bwie.jd_demo.mvp.my.presenter;

import android.annotation.SuppressLint;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.my.model.MyModel;
import com.bwie.jd_demo.mvp.my.model.bean.UploadBean;
import com.bwie.jd_demo.mvp.my.model.bean.UserInfoBean;
import com.bwie.jd_demo.mvp.my.view.iview.MyView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class MyPresenter extends BasePresenter<MyView> {

    private MyModel myModel;

    public MyPresenter(MyView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        myModel = new MyModel();
    }

    @SuppressLint("CheckResult")
    public void TuijainNet() {
        myModel.TuijainNet()
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
                                view.Success(adBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("请求失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void UpLoadDataNet(int uid, MultipartBody.Part part) {
        myModel.UpLoadDataNet(uid, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UploadBean uploadBean) {
                        if ("0".equals(uploadBean.getCode())) {
                            if (view != null) {
                                view.UpLoadSuccess(uploadBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("上传失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("上传失败");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void UserInfoData(int uid) {
        myModel.UserInfoData(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        if ("0".equals(userInfoBean.getCode())) {
                            if (view != null) {
                                view.UserInfoSuccess(userInfoBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("获取信息失败!");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
