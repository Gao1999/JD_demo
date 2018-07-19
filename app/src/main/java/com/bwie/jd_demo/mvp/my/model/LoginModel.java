package com.bwie.jd_demo.mvp.my.model;

import com.bwie.jd_demo.mvp.my.model.bean.LoginBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;

public class LoginModel {

    public Observable<LoginBean> loginDataNet(String mobile, String password) {
        return RetrofitManager.getDefault().create(IMyApi.class).loginDataNet(mobile, password);
    }
}
