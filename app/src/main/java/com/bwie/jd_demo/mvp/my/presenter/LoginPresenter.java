package com.bwie.jd_demo.mvp.my.presenter;

import android.annotation.SuppressLint;

import com.bwie.jd_demo.base.BasePresenter;
import com.bwie.jd_demo.mvp.my.model.LoginModel;
import com.bwie.jd_demo.mvp.my.model.bean.LoginBean;
import com.bwie.jd_demo.mvp.my.view.iview.LoginView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel loginModel;

    public LoginPresenter(LoginView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @SuppressLint("CheckResult")
    public void loginDataNet(String mobile, String password) {
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
        loginModel.loginDataNet(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if ("0".equals(loginBean.getCode())) {
                            if (view != null) {
                                view.getSuccess(loginBean);
                            }
                        } else {
                            if (view != null) {
                                view.getError("登录失败!");
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
