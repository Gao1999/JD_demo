package com.bwie.jd_demo.mvp.my.model;

import com.bwie.jd_demo.mvp.my.model.bean.RegBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;

public class RegModel {
    public Observable<RegBean> RegDataNet(String mobile, String password) {
        return RetrofitManager.getDefault().create(IMyApi.class).RegDataNet(mobile, password);
    }
}
