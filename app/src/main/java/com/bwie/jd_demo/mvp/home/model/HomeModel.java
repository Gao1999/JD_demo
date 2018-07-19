package com.bwie.jd_demo.mvp.home.model;

import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.home.model.bean.ClassBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;

public class HomeModel {
    //轮播图
    public Observable<AdBean> AdFromDataNet() {
        return RetrofitManager.getDefault().create(IHomeApi.class).adFromNet();
    }

    //九宫格
    public Observable<ClassBean> ClassFromDataNet() {
        return RetrofitManager.getDefault().create(IHomeApi.class).ClassFromNet();
    }
}
