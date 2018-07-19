package com.bwie.jd_demo.mvp.Find.presenter;

import android.annotation.SuppressLint;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.Find.model.FindModel;
import com.bwie.jd_demo.mvp.Find.model.bean.NewsBean;
import com.bwie.jd_demo.mvp.Find.model.bean.WelfareBean;
import com.bwie.jd_demo.mvp.Find.view.iview.FindView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FindPresenter extends BasePresenter<FindView> {

    private FindModel findModel;

    public FindPresenter(FindView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        findModel = new FindModel();
    }

    @SuppressLint("CheckResult")
    public void NewsData(String url) {
        findModel.NewsData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if (newsBean.getError_code() == 0) {
                            if (view != null) {
                                view.NewSuccess(newsBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("请求失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("请求失败.");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void WelfareNet(String url) {
        findModel.WelfareNet(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WelfareBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WelfareBean welfareBean) {
                        if (welfareBean.getCode() == 200) {
                            if (view != null) {
                                view.WelfareSuccess(welfareBean);
                            }
                        } else {
                            if (view != null) {
                                view.Error("请求失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.Error("请求失败.");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
