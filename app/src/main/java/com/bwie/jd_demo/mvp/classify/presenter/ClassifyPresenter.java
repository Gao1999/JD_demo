package com.bwie.jd_demo.mvp.classify.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.classify.model.ClassifyModel;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyLeftBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;
import com.bwie.jd_demo.mvp.classify.view.iview.ClassifyView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassifyPresenter extends BasePresenter<ClassifyView> {

    private ClassifyModel classifyModel;

    public ClassifyPresenter(ClassifyView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        classifyModel = new ClassifyModel();
    }

    @SuppressLint("CheckResult")
    public void classifyNetLeft() {
        classifyModel.classifyNetLeft()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyLeftBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ClassifyLeftBean classifyLeftBean) {
                        if ("0".equals(classifyLeftBean.getCode())) {
                            if (view != null) {
                                view.getSuccess(classifyLeftBean);
                            }
                        } else {
                            if (view != null) {
                                view.getError("请求数据失败");
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
    public void ClassifyNetFromRight(int cid) {
        classifyModel.ClassifyNetFromRight(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyRightBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ClassifyRightBean classifyRightBean) {
                        if ("0".equals(classifyRightBean.getCode())) {
                            if (view != null) {
                                view.Success(classifyRightBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("请求数据失败");
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

    public void Classifyproduct(int pscid, int page, int sort) {
        classifyModel.Classifyproduct(pscid, page, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ClassifyBean classifyBean) {
                        Log.e("log", "Classifyproduct_onNext:------ " + classifyBean.getData().toString());
                        if (view != null) {
                            view.SUCCESS(classifyBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("log", "Classifyproduct_onError: "+e.getMessage() );
                        if (view != null) {
                            view.Error("请求失败!123");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
