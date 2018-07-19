package com.bwie.jd_demo.mvp.my.presenter;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.my.model.RegModel;
import com.bwie.jd_demo.mvp.my.model.bean.RegBean;
import com.bwie.jd_demo.mvp.my.view.iview.RegView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegPresenter extends BasePresenter<RegView> {

    private RegModel regModel;

    public RegPresenter(RegView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        regModel = new RegModel();
    }

    public void RegDataNet(String mobile, String password) {
        if (mobile.equals("") && mobile.length() < 1) {
            if (view != null) {
                view.getError("手机号不能为空...");
            }
            return;
        }
        if (mobile.length() < 11) {
            if (view != null) {
                view.getError("手机号长度不能少于11位...");
            }
            return;
        }
        if (password.equals("") && password.length() < 1) {
            if (view != null) {
                view.getError("密码不能为空...");
            }
            return;
        }
        if (password.length() < 6) {
            if (view != null) {
                view.getError("密码长度不能少于6位...");
            }
            return;
        }
        regModel.RegDataNet(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        if ("0".equals(regBean.getCode())) {
                            if (view != null) {
                                view.getSuccess(regBean);
                            }
                        } else {
                            if (view != null) {
                                view.getError("注册失败!");
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
}
